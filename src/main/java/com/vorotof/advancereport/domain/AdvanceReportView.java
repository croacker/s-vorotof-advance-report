package com.vorotof.advancereport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Авансовый отчет, sql-view.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class AdvanceReportView {
    /**
     * Идентификатор строки чека.
     */
    @Id
    private Long id;

    /**
     * Идентификатор чека.
     */
    private Long checkId;

    /**
     * fiscalDocumentNumber.
     */
    private String fiscalDocumentNumber;

    /**
     * Дата-время.
     */
    private LocalDateTime checkDate;

    /**
     * Идентификатор кассира.
     */
    private Long cashierId;

    /**
     * Идентификатор магазина.
     */
    private Long shopId;

    /**
     * Идентификатор магазина.
     */
    private Long organizationId;

    /**
     * Сумма.
     */
    private Integer checkTotalSum;

    /**
     * Сумма наличные.
     */
    private Integer cashSum;

    /**
     * Сумма безналичные.
     */
    private Integer ecashSum;

    /**
     * Идентификатор товара.
     */
    private Long productId;

    /**
     * Наименование товара.
     */
    private String productName;

    /**
     * Цена.
     */
    private Integer price;

    /**
     * Количество.
     */
    private Integer quantity;

    /**
     * Сумма.
     */
    private Integer lineTotalSum;

}
