package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.check.AddCashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckInfoDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CheckService {

    List<CashCheckInfoDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    CashCheckInfoDto findById(Long id);

    CashCheckDto findCheck(String kktRegId, String fiscalDriveNumber, String fiscalDocumentNumber);

    CashCheckDto save(AddCashCheckDto dto);

    CashCheckDto update(CashCheckDto dto);

    CashCheckDto delete(Long id);
}
