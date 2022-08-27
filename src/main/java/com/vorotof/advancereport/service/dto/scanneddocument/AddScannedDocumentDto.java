package com.vorotof.advancereport.service.dto.scanneddocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * РаспознанныйДокумент из 1С
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "РаспознанныйДокумент из 1С")
public class AddScannedDocumentDto {

    /**
     * Дата документа 1С.
     */
    @Schema(description = "Дата документа 1С", example = "12.07.2022 6:54:21")
    private String date;

    /**
     * Номер документа 1С.
     */
    @Schema(description = "Номер документа 1С", example = "0000001")
    private String number;

    /**
     * Дата распознанного документа.
     */
    @Schema(description = "Дата распознанного документа", example = "12.07.2022")
    private String dateDoc;

    /**
     * Номер распознанного документа.
     */
    @Schema(description = "Номер распознанного документа", example = "S64.0000101")
    private String numberDoc;

    /**
     * Тип распознанного документа.
     */
    @Schema(description = "Тип распознанного документа", example = "Счет-фактура")
    private String typeDoc;

    /**
     * Описание/наименвание документа(Документ № 45-15949 от 30.06.2022 на сумму 721,48).
     */
    @Schema(description = "Описание/наименвание документа", example = "Документ № 45-15949 от 30.06.2022 на сумму 721,48")
    private String descriptionDoc;


    /**
     * Контрагент
     */
    @Schema(description = "Контрагент", example = "ООО \"Деловые решения\"")
    private String contractor;

    /**
     * Организация
     */
    @Schema(description = "Организация", example = "ООО \"ЫЫЫ\"")
    private String organization;

    /**
     * Продавец ИНН
     */
    @Schema(description = "Продавец ИНН", example = "2222222222")
    private String sellerNumber;

    /**
     * Покупатель ИНН
     */
    @Schema(description = "Покупатель ИНН", example = "2222222222")
    private String buyerNumber;

    /**
     * Таблица номенклатуры в виде json
     */
    @Schema(description = "Таблица номенклатуры в виде json", example = "{}")
    private String nomenclatureTable;

}
