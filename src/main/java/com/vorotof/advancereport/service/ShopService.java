package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Сервис магазинов.
 */
public interface ShopService {

    List<ShopDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    ShopDto findOne(Long id);

    ShopDto findByName(String name);

    ShopDto findByAddress(String address);

    ShopDto save(AddShopDto dto);

    ShopDto update(ShopDto dto);

    ShopDto delete(Long id);

    List<ShopDto> getShops(String expression, Pageable pageable);
}
