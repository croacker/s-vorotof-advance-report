package com.vorotof.advancereport.service.mapper.productprice;

import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProductPrice implements Mapper<ProductPriceDto, ProductPrice> {

    @Override
    public ProductPrice map(ProductPriceDto input) {
        return new ProductPrice()
                .setId(input.getId())
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate());
    }

}
