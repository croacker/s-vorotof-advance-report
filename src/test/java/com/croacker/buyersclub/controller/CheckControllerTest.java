package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.CheckService;
import com.vorotof.advancereport.service.dto.check.AddCashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(CheckController.class)
class CheckControllerTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Autowired
    WebTestClient client;

    @MockBean
    private CheckService service;

    @Test
    public void shouldReturnAllChecks() {
        // given
        var given1 = createCashCheckInfoDto(1L);
        var given2 = createCashCheckInfoDto(2L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/check")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(cashiers -> cashiers.size(), equalTo(2));
    }

    @Test
    public void shouldReturnCheck() {
        // given
        var expected = createCashCheckInfoDto(1L);
        when(service.findOne(any())).thenReturn(expected);

        // when and then
        client.get()
                .uri("/api/v1/check/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashCheckInfoDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldCreateCheck() {
        // given
        var given = createAddCashCheckDto(1L);
        var expected = createCashCheckDto(1L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/check")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashCheckDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldUpdateCheck() {
        // given
        var given = createCashCheckDto(1L);
        var expected = createCashCheckDto(1L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/check")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashCheckDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    @Test
    public void shouldDeleteCheck() {
        // given
        var expected = createCashCheckDto(1L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/check/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashCheckDto.class)
                .value(cashier -> cashier, equalTo(expected));
    }

    private CashCheckInfoDto createCashCheckInfoDto(long id) {
        return new CashCheckInfoDto()
                .setId(id)
                .setCashierId(1L)
                .setCashierName("test_cashier_" + id)
                .setCheckLines(Collections.emptyList())
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

    private AddCashCheckDto createAddCashCheckDto(long id) {
        return new AddCashCheckDto()
                .setCashierId(1L)
                .setCheckLines(Collections.emptyList())
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW);
    }

    private CashCheckDto createCashCheckDto(long id) {
        return new CashCheckDto()
                .setId(id)
                .setCashierId(1L)
                .setRequestNumber("test_request_number_" + id)
                .setShiftNumber("test_shift_number_" + id)
                .setKktRegId("test_kkt_reg_id_" + id)
                .setFiscalDriveNumber("test_fiscal_drive_number_" + id)
                .setFiscalDocumentNumber("test_fiscal_document_number_" + id)
                .setTotalSum(5)
                .setCashSum(3)
                .setEcashSum(2)
                .setCheckDate(NOW)
                .setDeleted(false);
    }

}