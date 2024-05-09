package heymart.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"heymart.backend"})
public class FoodGroceriesSupermarketProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodGroceriesSupermarketProductApplication.class, args);
	}

}
