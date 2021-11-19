package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String expression, Pageable pageable);

    List<Product> findByDeletedIsFalse(Pageable pageable);
}
