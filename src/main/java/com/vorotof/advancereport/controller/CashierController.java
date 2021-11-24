package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.CashierService;
import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashier")
@AllArgsConstructor
@Slf4j
public class CashierController implements CashierOperations {

    private final CashierService service;

    @Override
    public Flux<CashierDto> getAllCashiers(int page, int size, String sort, Sort.Direction direction) {
        return Flux.fromIterable(service.findAll(PageRequest.of(page, size, direction, sort)));
    }

    @Override
    public Mono<Long> getCashiersCount() {
        return service.getCount();
    }

    @Override
    public Mono<CashierDto> getCashier(Long id){
        return Mono.just(service.findOne(id));
    }

}
