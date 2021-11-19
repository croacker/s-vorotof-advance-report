package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductService {

    List<ProductDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    ProductDto findOne(Long id);

    ProductDto findByName(String name);

    ProductDto save(AddProductDto dto);

    ProductDto update(ProductDto dto);

    ProductDto delete(Long id);
}
