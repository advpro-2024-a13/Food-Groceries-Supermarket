package heymart.backend.service;

import heymart.backend.models.Supermarket;
import heymart.backend.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    @Autowired
    private final SupermarketRepository supermarketRepository;

    public SupermarketServiceImpl(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    @Override
    public Optional<Supermarket> findById(Long id) {
        return supermarketRepository.findById(id);
    }

    @Override
    public List<Supermarket> findAll() {
        return supermarketRepository.findAll();
    }

    @Override
    public Supermarket save(Supermarket supermarket) {
        return supermarketRepository.save(supermarket);
    }

    @Override
    public void deleteById(Long id) {
        supermarketRepository.deleteById(id);
    }
}