package com.vorotof.advancereport.service.mapper.scanneddocument;

import com.vorotof.advancereport.domain.ScannedDocument;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.mapper.Mapper;

public class ScannedDocumentToDto implements Mapper<ScannedDocument, ScannedDocumentDto> {

    @Override
    public ScannedDocumentDto map(ScannedDocument input) {
        return new ScannedDocumentDto()
                .setId(input.getId())
                .setDate(input.getDate())
                .setNumber(input.getNumber())
                .setDateDoc(input.getDateDoc())
                .setNumberDoc(input.getNumberDoc())
                .setTypeDoc(input.getTypeDoc())
                .setDescriptionDoc(input.getDescriptionDoc())
                .setContractor(input.getContractor())
                .setOrganization(input.getOrganization())
                .setSellerNumber(input.getSellerNumber())
                .setBuyerNumber(input.getBuyerNumber())
                .setNomenclatureTable(input.getNomenclatureTable())
                .setCreatedAt(input.getCreatedAt())
                .setUpdatedAt(input.getUpdatedAt())
                .setDeleted(input.getDeleted());
    }

}
