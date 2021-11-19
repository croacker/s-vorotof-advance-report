package com.vorotof.advancereport.service.mapper.productprice;

import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceToDto implements Mapper<ProductPrice, ProductPriceDto> {

    @Override
    public ProductPriceDto map(ProductPrice input) {
        return new ProductPriceDto()
                .setId(input.getId())
                .setShopId(input.getShop().getId())
                .setProductId(input.getProduct().getId())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate())
                .setDeleted(input.getDeleted());
    }

}
