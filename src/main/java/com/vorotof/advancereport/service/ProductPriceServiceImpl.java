package com.vorotof.advancereport.service;

import com.vorotof.advancereport.repo.ProductPriceRepo;
import com.vorotof.advancereport.repo.ProductPriceViewRepo;
import com.vorotof.advancereport.repo.ProductRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import com.vorotof.advancereport.service.mapper.productprice.AddDtoToProductPrice;
import com.vorotof.advancereport.service.mapper.productprice.DtoToProductPrice;
import com.vorotof.advancereport.service.mapper.productprice.ProductPriceToDto;
import com.vorotof.advancereport.service.mapper.productprice.ProductPriceToInfoDto;
import com.vorotof.advancereport.service.mapper.telegram.ProductPriceToTelegramDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService{

    private final ProductPriceRepo repo;

    private final ProductPriceViewRepo viewRepo;

    private final ProductRepo productRepo;

    private final ShopRepo shopRepo;

    private final ProductPriceToDto toDtoMapper;

    private final ProductPriceToInfoDto toInfoDtoMapper;

    private final DtoToProductPrice toEntityMapper;

    private final AddDtoToProductPrice addToEntityMapper;

    private final ProductPriceToTelegramDto toTelegramDtoMapper;

    @Override
    public List<ProductPriceInfoDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public ProductPriceInfoDto findOne(Long id) {
        return repo.findById(id).map(toInfoDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public List<ProductPriceInfoDto> findByProduct(Long id) {
        var product = productRepo.findById(id).get();
        return StreamSupport.stream(repo.findByProduct(product).spliterator(), false)
                .map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public ProductPriceDto findPrice(ProductDto productDto, ShopDto shopDto, LocalDateTime priceDate) {
        var product = productRepo.findById(productDto.getId()).get(); //!!
        var shop = shopRepo.findById(shopDto.getId()).get();
        return repo.findByProductAndShopAndPriceDate(product, shop, priceDate).map(toDtoMapper).orElse(null);
    }

    @Override
    public ProductPriceDto save(AddProductPriceDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var product = productRepo.findById(dto.getProductId()).get();
        var productPrice = addToEntityMapper.map(dto)
                .setShop(shop)
                .setProduct(product)
                .setPrice(dto.getPrice())
                .setPriceDate(dto.getPriceDate())
                .setDeleted(false);
        productPrice = repo.save(productPrice);
        return toDtoMapper.map(productPrice);
    }

    @Override
    public ProductPriceDto update(ProductPriceDto dto) {
        var entity = toEntityMapper.map(dto);
        entity = repo.save(entity);
        return toDtoMapper.map(entity);
    }

    @Override
    public ProductPriceDto delete(Long id) {
        return repo.findById(id).map(cashier -> {
            cashier.setDeleted(true);
            cashier = repo.save(cashier);
            return toDtoMapper.map(cashier);
        }).orElse(null);
    }

    @Override
    public Long getProductsPricesCount(String expression) {
        return viewRepo.countByProductNameContainingIgnoreCase(expression);
    }

    @Override
    public List<TelegramProductPriceDto> getProductsPrices(String expression, Pageable pageable) {
        return viewRepo.findByProductNameContainingIgnoreCase(expression, pageable)
                .stream().map(toTelegramDtoMapper).collect(Collectors.toList());
    }
}
