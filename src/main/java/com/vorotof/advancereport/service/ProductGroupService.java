package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.productgroup.AddProductGroupDto;
import com.vorotof.advancereport.service.dto.productgroup.ProductGroupDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductGroupService {

    List<ProductGroupDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    ProductGroupDto findOne(Long id);

    ProductGroupDto save(AddProductGroupDto dto);

    ProductGroupDto update(ProductGroupDto dto);

    ProductGroupDto delete(Long id);

}
