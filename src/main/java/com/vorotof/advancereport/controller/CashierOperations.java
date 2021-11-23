package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Cashier", description = "Кассиры")
public interface CashierOperations {

    @Operation(operationId = "getAllCashiers", summary = "Список кассиров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кассиры не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<CashierDto> getAllCashiers(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "20") int size,
                                         @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                         @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getCashiersCount", summary = "Получить количество кассиров в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/count")
    Mono<Long> getCashiersCount();

    @Operation(operationId = "getCashier", summary = "Получить кассира по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кассир",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кассир не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<CashierDto> getCashier(@PathVariable Long id);

}
