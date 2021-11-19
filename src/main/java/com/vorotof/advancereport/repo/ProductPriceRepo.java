package com.vorotof.advancereport.repo;

import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.domain.Shop;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceRepo extends CrudRepository<ProductPrice, Long> {

    List<ProductPrice> findByDeletedIsFalse(Pageable pageable);

    List<ProductPrice> findByProduct(Product product);

    Optional<ProductPrice> findByProductAndShopAndPriceDate(Product product,
                                                            Shop shop,
                                                            LocalDateTime priceDate);
}
