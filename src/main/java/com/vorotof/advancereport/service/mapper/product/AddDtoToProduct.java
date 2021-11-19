package com.vorotof.advancereport.service.mapper.product;

import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToProduct implements Mapper<AddProductDto, Product> {

    @Override
    public Product map(AddProductDto input) {
        return new Product()
                .setName(input.getName());
    }

}
