package com.vorotof.advancereport.service;

import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.repo.CashierRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.mapper.cashier.AddDtoToCashier;
import com.vorotof.advancereport.service.mapper.cashier.CashierToDto;
import com.vorotof.advancereport.service.mapper.cashier.DtoToCashier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис кассиров.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CashierServiceImpl implements CashierService {

    private final CashierRepo repo;

    private final ShopRepo shopRepo;

    private final CashierToDto toDtoMapper;

    private final DtoToCashier toEntityMapper;

    private final AddDtoToCashier addToEntityMapper;

    @Override
    public List<CashierDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public CashierDto findOne(Long id) {
        return repo.findById(id).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public CashierDto findByNameAndShopId(String name, Long shopId) {
        Shop shop = shopRepo.findById(shopId).get();
        return repo.findByNameAndShopId(name, shopId).map(toDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public CashierDto save(AddCashierDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var cashier = addToEntityMapper.map(dto)
                .setShop(shop)
                .setDeleted(false);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto update(CashierDto dto) {
        var shop = shopRepo.findById(dto.getShopId()).get();
        var cashier = toEntityMapper.map(dto).setShop(shop);
        cashier = repo.save(cashier);
        return toDtoMapper.map(cashier);
    }

    @Override
    public CashierDto delete(Long id) {
        return repo.findById(id).map(cashier -> {
            cashier.setDeleted(true);
            cashier = repo.save(cashier);
            return toDtoMapper.map(cashier);
        }).orElse(null);
    }
}
