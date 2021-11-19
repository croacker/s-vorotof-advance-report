package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис товаров.
 */
public interface ProductPriceService {

    List<ProductPriceInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    ProductPriceInfoDto findOne(Long id);

    List<ProductPriceInfoDto> findByProduct(Long id);

    ProductPriceDto findPrice(ProductDto product, ShopDto shop, LocalDateTime dateTime);

    ProductPriceDto save(AddProductPriceDto dto);

    ProductPriceDto update(ProductPriceDto dto);

    ProductPriceDto delete(Long id);

    Long getProductsPricesCount(String expression);

    List<TelegramProductPriceDto> getProductsPrices(String expression, Pageable pageable);
}
