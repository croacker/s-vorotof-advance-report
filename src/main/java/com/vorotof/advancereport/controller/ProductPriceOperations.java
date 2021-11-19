package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "ProductPrice", description = "Товары")
public interface ProductPriceOperations {

    @Operation(operationId = "listProductPrices", summary = "Список цен товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цены товаров",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товары не найдены", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping
    Flux<ProductPriceInfoDto> getAllProductPrices(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "20") int size,
                                                  @RequestParam(value = "sort", defaultValue = "priceDate") String sort,
                                                  @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction);

    @Operation(operationId = "getProductPricesCount", summary = "Получить количество цен товаров в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashierDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/count")
    Mono<Long> getProductPricesCount();

    @Operation(operationId = "getProductPrice", summary = "Получить цены товаров по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цены товаров",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductPriceDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товар не найдена", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @GetMapping(path = "/{id}")
    Mono<ProductPriceInfoDto> getProductPrice(@PathVariable Long id);

    @Operation(operationId = "createProductPrice", summary = "Добавить цену товара",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цена товара",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductPriceDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PostMapping
    Mono<ProductPriceDto> createProductPrice(@RequestBody AddProductPriceDto dto);

    @Operation(operationId = "updateProductPrice", summary = "Обновить цену товара",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цена товара",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductPriceDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)
    })
    @PutMapping
    Mono<ProductPriceDto> updateProductPrice(@RequestBody ProductPriceDto dto);

    @Operation(operationId = "deleteProductPrice", summary = "Удалить цену товара по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цена товара",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductPriceDto.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе", content = @Content),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = @Content),
            @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = @Content)})
    @DeleteMapping(path = "/{id}")
    Mono<ProductPriceDto> deleteProductPrice(@PathVariable Long id);

}
