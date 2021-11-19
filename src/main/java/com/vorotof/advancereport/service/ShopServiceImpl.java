package com.vorotof.advancereport.service;

import com.vorotof.advancereport.repo.OrganizationRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.shop.AddDtoToShop;
import com.vorotof.advancereport.service.mapper.shop.DtoToShop;
import com.vorotof.advancereport.service.mapper.shop.ShopToDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService{

    private final ShopRepo repo;

    private final OrganizationRepo organizationRepo;

    private final ShopToDto toDtoMapper;

    private final DtoToShop toShopMapper;

    private final AddDtoToShop addToEntityMapper;

    @Override
    public List<ShopDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public ShopDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public ShopDto findByName(String name) {
        return repo.findFirstByName(name).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto findByAddress(String address) {
        return repo.findFirstByAddress(address).map(toDtoMapper).orElse(null);
    }

    @Override
    public ShopDto save(AddShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = addToEntityMapper.map(dto)
                .setOrganization(organization).setDeleted(false);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto update(ShopDto dto) {
        var organization = organizationRepo.findById(dto.getOrganizationId()).get();
        var shop = toShopMapper.map(dto)
                .setOrganization(organization);
        shop = repo.save(shop);
        return toDtoMapper.map(shop);
    }

    @Override
    public ShopDto delete(Long id) {
        return repo.findById(id).map(shop -> {
            shop.setDeleted(true);
            shop = repo.save(shop);
            return toDtoMapper.map(shop);
        }).orElse(null);
    }

    @Override
    public List<ShopDto> getShops(String expression, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(expression, pageable)
                .stream().map(toDtoMapper).collect(Collectors.toList());
    }
}
