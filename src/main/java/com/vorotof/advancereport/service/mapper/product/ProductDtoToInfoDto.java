package com.vorotof.advancereport.service.mapper.product;

import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.product.ProductInfoDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoToInfoDto implements Mapper<Product, ProductInfoDto> {
    @Override
    public ProductInfoDto map(Product input) {
        return new ProductInfoDto()
                .setId(input.getId())
                .setName(input.getName())
                .setProductGroupId(getProductGroupId(input))
                .setProductGroupName(getProductGroupName(input))
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

    private Long getProductGroupId(Product input) {
        Long result = null;
        if (input.getProductGroup() != null) {
            result = input.getProductGroup().getId();
        }
        return result;
    }

    private String getProductGroupName(Product input) {
        String result = null;
        if (input.getProductGroup() != null) {
            result = input.getProductGroup().getName();
        }
        return result;
    }
}
