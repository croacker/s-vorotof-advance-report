package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "AdvanceReport", description = "Авансовый отчет")
public interface AdvanceReportOperations {

    @Operation(operationId = "getAdvanceReport", summary = "Авансовый отчет")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Авансовый отчет",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные за период не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<AdvanceReportViewDto> getAdvanceReport(@RequestParam(value = "beginDate", defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate beginDate,
                                                @RequestParam(value = "endDate", defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate endDate);

}
