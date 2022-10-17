package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.dto.scanneddocument.AddScannedDocumentDto;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * РаспознанныйДокумент из 1С
 */
@Tag(name = "ScannedDocument", description = "РаспознанныйДокумент из 1С")
public interface ScannedDocumentOperations {

    @Operation(operationId = "listShops", summary = "Список РаспознанныхДокументов из 1С")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список РаспознанныхДокументов из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "РаспознанныеДокументы не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<ScannedDocumentDto> getAllScannedDocuments(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                                    @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                    @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getShopsCount", summary = "Получить количество РаспознанныхДокументов из 1С в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество РаспознанныхДокументов из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/count")
    Mono<Long> getScannedDocumentsCount();

    @Operation(operationId = "getShop", summary = "Получить РаспознанныйДокумент из 1С по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныйДокумент из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Магазин не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<ScannedDocumentDto> getScannedDocument(@PathVariable Long id);

    @Operation(operationId = "getShop", summary = "Получить РаспознанныйДокумент из 1С по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныйДокумент из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShopDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Магазин не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/description-doc/{descriptionDoc}")
    Mono<ScannedDocumentDto> getScannedDocumentByDescriptionDoc(@PathVariable String descriptionDoc);

    @Operation(operationId = "getBetweenDate", summary = "Получить РаспознанныеДокументы из 1С за период")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныеДокументы из 1С за период",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные за период не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/between")
    Flux<ScannedDocumentDto> getBetweenDate(@RequestParam(value = "beginDate")
                                                @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate beginDate,
                                                @RequestParam(value = "endDate")
                                                @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate);

    @Operation(operationId = "getIdsBetweenDate", summary = "Получить идентификаторы РаспознанныхДокументов из 1С за период")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Идентификаторы РаспознанныхДокументов из 1С за период",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Данные за период не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/between/id")
    Flux<Long> getIdsBetweenDate(@RequestParam(value = "beginDate")
                                                @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate beginDate,
                                                @RequestParam(value = "endDate")
                                                @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate);

    @Operation(operationId = "createOrganization", summary = "Добавить РаспознанныйДокумент из 1С",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныйДокумент из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScannedDocumentDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<ScannedDocumentDto> createScannedDocument(@RequestBody AddScannedDocumentDto dto);

    @Operation(operationId = "updateOrganization", summary = "Обновить РаспознанныйДокумент из 1С",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныйДокумент из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScannedDocumentDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<ScannedDocumentDto> updateScannedDocument(@RequestBody ScannedDocumentDto dto);

    @Operation(operationId = "deleteOrganization", summary = "Удалить РаспознанныйДокумент из 1С по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "РаспознанныйДокумент из 1С",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScannedDocumentDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Организация не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<ScannedDocumentDto> deleteScannedDocument(@PathVariable Long id);
}
