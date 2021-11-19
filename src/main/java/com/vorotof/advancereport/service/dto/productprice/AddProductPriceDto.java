package com.vorotof.advancereport.service.dto.productprice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Добавление цены на товар.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Цена на товар")
public class AddProductPriceDto {
    /**
     * Идентификатор магазина.
     */
    @Schema(description = "Идентификатор магазина", example = "1")
    private Long shopId;

    /**
     * Идентификатор товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private Long productId;

    /**
     * Цена.
     */
    @Schema(description = "Цена", example = "1")
    private Integer price;

    /**
     * Дата-время.
     */
    @Schema(description = "Дата-время")
    private LocalDateTime priceDate;
}
