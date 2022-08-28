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
 * РаспознанныйДокумент из 1С
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class ScannedDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата документа 1С
     */
    private LocalDateTime date;

    /**
     * Номер документа 1С
     */
    private String number;

    /**
     * Дата распознанного документа
     */
    private LocalDateTime dateDoc;

    /**
     * Номер распознанного документа
     */
    private String numberDoc;

    /**
     * Тип распознанного документа
     */
    private String typeDoc;

    /**
     * Описание/наименвание документа(Документ № 45-15949 от 30.06.2022 на сумму 721,48)
     */
    private String descriptionDoc;

    /**
     * Контрагент
     */
    private String contractor;

    /**
     * Организация
     */
    private String organization;

    /**
     * Продавец ИНН
     */
    private String sellerNumber;

    /**
     * Покупатель ИНН
     */
    private String buyerNumber;

    /**
     * Таблица номенклатуры в виде json
     */
    private String nomenclatureTable;

}
