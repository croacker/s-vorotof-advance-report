package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.Cashier;
import com.vorotof.advancereport.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Кассиры.
 */
public interface CashierRepo extends CrudRepository<Cashier, Long> {

    List<Cashier> findByDeletedIsFalse(Pageable pageable);

    Optional<Cashier> findByNameAndShopId(String name, Long shopId);

}
