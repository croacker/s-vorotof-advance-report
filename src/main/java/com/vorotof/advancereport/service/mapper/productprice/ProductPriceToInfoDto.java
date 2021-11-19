package com.vorotof.advancereport.service.mapper.productprice;

import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceToInfoDto implements Mapper<ProductPrice, ProductPriceInfoDto> {

    @Override
    public ProductPriceInfoDto map(ProductPrice input) {
        return new ProductPriceInfoDto()
                .setId(input.getId())
                .setShopId(input.getShop().getId())
                .setShopName(input.getShop().getName())
                .setProductId(input.getProduct().getId())
                .setProductName(input.getProduct().getName())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate())
                .setDeleted(input.getDeleted());
    }

}
