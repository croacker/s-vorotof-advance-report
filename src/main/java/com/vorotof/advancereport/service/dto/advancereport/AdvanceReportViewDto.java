package com.vorotof.advancereport.service.dto.advancereport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Авансовый отчет.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Авансовый отчет")
public class AdvanceReportViewDto {
    /**
     * Идентификатор.
     */
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    /**
     * Идентификатор чека.
     */
    @Schema(description = "Идентификатор чека", example = "1")
    private Long checkId;

    /**
     * fiscalDocumentNumber.
     */
    @Schema(description = "fiscalDocumentNumber", example = "784274")
    private String fiscalDocumentNumber;

    /**
     * Дата-время.
     */
    @Schema(description = "Сумма безналичные")
    private LocalDateTime checkDate;

    /**
     * Идентификатор кассира.
     */
    @Schema(description = "Идентификатор кассира", example = "1")
    private Long cashierId;

    /**
     * Идентификатор магазина.
     */
    @Schema(description = "Идентификатор магазина", example = "1")
    private Long shopId;

    /**
     * Идентификатор организации.
     */
    @Schema(description = "Идентификатор организации", example = "1")
    private Long organizationId;

    /**
     * Сумма чека.
     */
    @Schema(description = "Сумма чека", example = "10000")
    private Integer checkTotalSum;

    /**
     * Сумма наличные.
     */
    @Schema(description = "Сумма наличные", example = "5000")
    private Integer cashSum;

    /**
     * Сумма безналичные.
     */
    @Schema(description = "Сумма безналичные", example = "5000")
    private Integer ecashSum;

    /**
     * Идентификатор товара.
     */
    @Schema(description = "Идентификатор товара", example = "1")
    private Long productId;

    /**
     * Наименование товара.
     */
    @Schema(description = "Наименование товара", example = "Товар 1")
    private String productName;

    /**
     * Цена.
     */
    @Schema(description = "Цена", example = "10000")
    private Integer price;

    /**
     * Количество.
     */
    @Schema(description = "Количество", example = "2")
    private Integer quantity;

    /**
     * Сумма.
     */
    @Schema(description = "Сумма", example = "20000")
    private Integer lineTotalSum;
}
