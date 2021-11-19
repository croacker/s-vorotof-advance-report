package com.vorotof.advancereport.service.mapper.productgroup;

import com.vorotof.advancereport.domain.ProductGroup;
import com.vorotof.advancereport.service.dto.productgroup.ProductGroupDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupToDto implements Mapper<ProductGroup, ProductGroupDto> {

    @Override
    public ProductGroupDto map(ProductGroup input) {
        return new ProductGroupDto()
                .setId(input.getId())
                .setName(input.getName())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}