package com.vorotof.advancereport.service.mapper.product;

import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToProduct implements Mapper<ProductDto, Product> {

    @Override
    public Product map(ProductDto input) {
        return new Product()
                .setId(input.getId())
                .setName(input.getName())
                .setDeleted(input.getDeleted());
    }

}
