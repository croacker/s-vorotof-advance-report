package com.vorotof.advancereport.service;

import com.vorotof.advancereport.domain.ProductGroup;
import com.vorotof.advancereport.repo.ProductGroupRepo;
import com.vorotof.advancereport.repo.ProductRepo;
import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.mapper.product.AddDtoToProduct;
import com.vorotof.advancereport.service.mapper.product.DtoToProduct;
import com.vorotof.advancereport.service.mapper.product.ProductToDto;
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
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepo repo;

    private final ProductGroupRepo productGroupRepo;

    private final ProductToDto toDtoMapper;

    private final DtoToProduct toEntityMapper;

    private final AddDtoToProduct addToEntityMapper;

    @Override
    public List<ProductDto> findAll(Pageable pageable) {
        return StreamSupport.stream(
                repo.findByDeletedIsFalse(pageable).spliterator(), false)
                .map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public ProductDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public ProductDto findByName(String name) {
        return repo.findByName(name).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public ProductDto save(AddProductDto dto) {
        ProductGroup productGroup = null;
        if(dto.getProductGroupId() != null) {
            productGroup = productGroupRepo.findById(dto.getProductGroupId()).orElse(null);
        }
        var product = addToEntityMapper.map(dto)
                .setProductGroup(productGroup).setDeleted(false);
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductDto update(ProductDto dto) {
        ProductGroup productGroup = null;
        if(dto.getProductGroupId() != null) {
            productGroup = productGroupRepo.findById(dto.getProductGroupId()).orElse(null);
        }
        var product = toEntityMapper.map(dto)
                .setProductGroup(productGroup);
        product = repo.save(product);
        return toDtoMapper.map(product);
    }

    @Override
    public ProductDto delete(Long id) {
        return repo.findById(id).map(organization -> {
            organization.setDeleted(true);
            organization = repo.save(organization);
            return toDtoMapper.map(organization);
        }).orElse(null);
    }
}
