package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends CrudRepository<Shop, Long> {

    Optional<Shop> findFirstByAddress(String address);

    Optional<Shop> findFirstByName(String name);

    List<Shop> findByNameContainingIgnoreCase(String expression, Pageable pageable);

    List<Shop> findByDeletedIsFalse(Pageable pageable);
}
