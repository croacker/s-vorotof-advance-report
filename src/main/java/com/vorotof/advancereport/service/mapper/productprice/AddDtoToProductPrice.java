package com.vorotof.advancereport.service.mapper.productprice;

import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProductPrice implements Mapper<AddProductPriceDto, ProductPrice> {

    @Override
    public ProductPrice map(AddProductPriceDto input) {
        return new ProductPrice()
                .setPrice(input.getPrice())
                .setPriceDate(input.getPriceDate());
    }

}
