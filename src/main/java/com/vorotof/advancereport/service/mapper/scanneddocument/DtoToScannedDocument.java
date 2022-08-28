package com.vorotof.advancereport.service.mapper.scanneddocument;

import com.vorotof.advancereport.domain.ScannedDocument;
import com.vorotof.advancereport.service.dto.scanneddocument.ScannedDocumentDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DtoToScannedDocument implements Mapper<ScannedDocumentDto, ScannedDocument> {

    @Override
    public ScannedDocument map(ScannedDocumentDto input) {
        return new ScannedDocument()
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
                .setNomenclatureTable(input.getNomenclatureTable());
    }

}
