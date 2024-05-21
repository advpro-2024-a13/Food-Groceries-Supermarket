package heymart.backend.security.jwt;

import heymart.backend.models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Setter
public class AuthTokenFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    private final RestClient restClient;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public AuthTokenFilter(JwtUtils jwtUtils, RestClient restClient) {
        this.restClient = restClient;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                User user = restClient.get()
                        .uri("https://a13autehnticate-6yfvrprlfa-uc.a.run.app/api/auth/getUserByJwt")
                        .header("Authorization", authHeader)
                        .retrieve()
                        .toEntity(User.class)
                        .getBody();

                if (user == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                String authority = user.getAuthorities().iterator().next().getAuthority();
                if (authority.startsWith("ROLE_")) {
                    authority = authority.substring(5);
                }
                UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(authority).build();

                if (jwtUtils.validateJwtToken(jwt)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
