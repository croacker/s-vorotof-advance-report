package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.ProductPriceView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPriceViewRepo extends CrudRepository<ProductPriceView, Long> {

    Long countByProductNameContainingIgnoreCase(String expression);

    List<ProductPriceView> findByProductNameContainingIgnoreCase(String expression, Pageable pageable);

}
