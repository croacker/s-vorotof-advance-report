package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Сервис кассиров.
 */
public interface CashierService {

    List<CashierDto> findAll(Pageable pageable);

    Mono<Long> getCount();

    CashierDto findOne(Long id);

    CashierDto findByName(String name);

    CashierDto save(AddCashierDto dto);

    CashierDto update(CashierDto dto);

    CashierDto delete(Long id);

}
