package com.vorotof.advancereport.service.mapper.check;


import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.CashCheck;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class DtoToCashCheckTest {

    private DtoToCashCheck mapper;

    private static final LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        mapper = new DtoToCashCheck();
    }

    @Test
    void shouldMapDto() {
        //given
        var given = createDto();
        var expected = createEntity();

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private CashCheck createEntity() {
        return new CashCheck()
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

    private CashCheckDto createDto() {
        return new CashCheckDto()
                .setId(0L)
                .setKktRegId("test_kkt_reg_id")
                .setFiscalDriveNumber("test_fiscal_drive_number")
                .setFiscalDocumentNumber("test_fiscal_document_number")
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

}
