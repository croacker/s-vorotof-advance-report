package com.vorotof.advancereport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * РаспознанныйДокумент из 1С
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ScannedDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата документа 1С
     */
    private String date;

    /**
     * Номер документа 1С
     */
    private String number;

    /**
     * Дата распознанного документа
     */
    private String dateDoc;

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

    /**
     * Создан.
     */
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Обновлен.
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Пометка на удаление.
     */
    private Boolean deleted;
}
