package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.ProductPriceService;
import com.vorotof.advancereport.service.ProductService;
import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController implements ProductOperations {

    private final ProductService service;

    private final ProductPriceService productPriceService;

    @Override
    public Flux<ProductDto> getAllProducts(int page, int size, String sort, Sort.Direction direction){
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<Long> getProductsCount() {
        return service.getCount();
    }

    @Override
    public Mono<ProductDto> getProduct(@PathVariable Long id){
        return Mono.just(service.findOne(id));
    }

}
