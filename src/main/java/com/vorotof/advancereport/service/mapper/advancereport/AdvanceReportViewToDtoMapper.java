package com.vorotof.advancereport.service.mapper.advancereport;

import com.vorotof.advancereport.domain.AdvanceReportView;
import com.vorotof.advancereport.service.dto.advancereport.AdvanceReportViewDto;
import com.vorotof.advancereport.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AdvanceReportViewToDtoMapper implements Mapper<AdvanceReportView, AdvanceReportViewDto> {

    @Override
    public AdvanceReportViewDto map(AdvanceReportView input) {
        return new AdvanceReportViewDto()
                .setId(input.getId())
                .setCheckId(input.getCheckId())
                .setFiscalDocumentNumber(input.getFiscalDocumentNumber())
                .setCheckDate(input.getCheckDate())
                .setCashierId(input.getCashierId())
                .setShopId(input.getShopId())
                .setOrganizationId(input.getOrganizationId())
                .setCheckTotalSum(input.getCheckTotalSum())
                .setCashSum(input.getCashSum())
                .setEcashSum(input.getEcashSum())
                .setProductId(input.getProductId())
                .setProductName(input.getProductName())
                .setPrice(input.getPrice())
                .setQuantity(input.getQuantity())
                .setLineTotalSum(input.getLineTotalSum());
    }
}
