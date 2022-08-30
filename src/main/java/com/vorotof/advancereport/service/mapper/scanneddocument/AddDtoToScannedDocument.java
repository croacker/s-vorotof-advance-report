package com.vorotof.advancereport.service.mapper.scanneddocument;

import com.vorotof.advancereport.domain.ScannedDocument;
import com.vorotof.advancereport.service.dto.scanneddocument.AddScannedDocumentDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddDtoToScannedDocument implements Mapper<AddScannedDocumentDto, ScannedDocument> {

    @Override
    public ScannedDocument map(AddScannedDocumentDto input) {
        return new ScannedDocument()
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
