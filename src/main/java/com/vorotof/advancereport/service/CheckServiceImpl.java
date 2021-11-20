package com.vorotof.advancereport.service;

import com.vorotof.advancereport.repo.CashierRepo;
import com.vorotof.advancereport.repo.CheckRepo;
import com.vorotof.advancereport.repo.ProductRepo;
import com.vorotof.advancereport.repo.TelegramUserRepo;
import com.vorotof.advancereport.service.dto.check.AddCashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckInfoDto;
import com.vorotof.advancereport.service.mapper.check.AddDtoToCashCheck;
import com.vorotof.advancereport.service.mapper.check.CashCheckToDto;
import com.vorotof.advancereport.service.mapper.check.CashCheckToInfoDto;
import com.vorotof.advancereport.service.mapper.check.DtoToCashCheck;
import com.vorotof.advancereport.service.mapper.checkline.AddDtoToCashCheckLine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис чеков.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CheckServiceImpl implements CheckService{

    private final CheckRepo repo;

    private final CashierRepo cashierRepo;

    private final ProductRepo productRepo;

    private final TelegramUserRepo telegramUserRepo;

    private final CashCheckToDto toDtoMapper;

    private final CashCheckToInfoDto toInfoDtoMapper;

    private final DtoToCashCheck toEntityMapper;

    private final AddDtoToCashCheck addToEntityMapper;

    private final AddDtoToCashCheckLine addLineToEntityMapper;

    @Override
    public List<CashCheckInfoDto> findAll(Pageable pageable) {
        return repo.findByDeletedIsFalse(pageable).stream().map(toInfoDtoMapper).collect(Collectors.toList());
    }

    @Override
    public Mono<Long> getCount() {
        return Mono.just(repo.count());
    }

    @Override
    public CashCheckInfoDto findById(Long id) {
        return repo.findById(id).map(toInfoDtoMapper).orElse(null); // TODO return Optional
    }

    @Override
    public CashCheckDto findCheck(String kktRegId, String fiscalDriveNumber, String fiscalDocumentNumber) {
        return repo.findByKktRegIdAndFiscalDriveNumberAndFiscalDocumentNumber(kktRegId,
                fiscalDriveNumber, fiscalDocumentNumber).map(toDtoMapper).orElse(null);
    }

    @Override
    public CashCheckDto save(AddCashCheckDto dto) {
        var cashier = cashierRepo.findById(dto.getCashierId()).get();
        var checkLines = dto.getCheckLines().stream().map(lineDto -> {
            var product = productRepo.findById(lineDto.getProductId()).get();
            return addLineToEntityMapper
                    .map(lineDto)
                    .setProduct(product);
        }).collect(Collectors.toList());
        var telegramUser = telegramUserRepo.findById(dto.getTelegramUserId()).orElse(null);
        var check = addToEntityMapper.map(dto)
                .setCashier(cashier)
                .setCheckLines(checkLines)
                .setTelegramUser(telegramUser)
                .setDeleted(false);
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CashCheckDto update(CashCheckDto dto) {
        var cashier = cashierRepo.findById(dto.getCashierId()).get();
        var telegramUser = telegramUserRepo.findById(dto.getTelegramUserId()).orElse(null);
        var check = toEntityMapper.map(dto)
                .setCashier(cashier)
                .setTelegramUser(telegramUser);
        check = repo.save(check);
        return toDtoMapper.map(check);
    }

    @Override
    public CashCheckDto delete(Long id) {
        return repo.findById(id).map(check -> {
            check.setDeleted(true);
            check = repo.save(check);
            return toDtoMapper.map(check);
        }).orElse(null);
    }
}
