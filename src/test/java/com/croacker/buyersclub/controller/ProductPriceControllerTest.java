package com.vorotof.advancereport.controller;

import com.vorotof.advancereport.service.ProductPriceService;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductPriceController.class)
class ProductPriceControllerTest {

    private final static LocalDateTime NOW = LocalDateTime.now();

    @Autowired
    WebTestClient client;

    @MockBean
    private ProductPriceService service;

    @Test
    public void shouldReturnAllProductPrices() {
        // given
        var given1 = createProductPriceInfoDto(1L);
        var given2 = createProductPriceInfoDto(2L);
        when(service.findAll(any())).thenReturn(Arrays.asList(given1, given2));

        // when and then
        client.get()
                .uri("/api/v1/product-price")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(productPrices -> productPrices.size(), equalTo(2));
    }

    @Test
    public void shouldReturnProductPrice() {
        // given
        var given = createProductPriceInfoDto(1L);
        when(service.findOne(any())).thenReturn(given);

        // when and then
        client.get()
                .uri("/api/v1/product-price/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductPriceDto.class)
                .value(productPrice -> productPrice.getPrice(), equalTo(100));
    }

    @Test
    public void shouldCreateProductPrice() {
        // given
        var given = createAddProductPriceDto(1L);
        var expected = createProductPriceDto(1L);
        when(service.save(given)).thenReturn(expected);

        // when and then
        client.post()
                .uri("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductPriceDto.class)
                .value(productPrice -> productPrice, equalTo(expected));
    }

    @Test
    public void shouldUpdateProductPrice() {
        // given
        var given = createProductPriceDto(1L);
        var expected = createProductPriceDto(1L);
        when(service.update(given)).thenReturn(expected);

        // when and then
        client.put()
                .uri("/api/v1/product-price")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(given))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductPriceDto.class)
                .value(productPrice -> productPrice, equalTo(expected));
    }

    @Test
    public void shouldDeleteProductPrice() {
        // given
        var expected = createProductPriceDto(1L).setDeleted(true);
        when(service.delete(1L)).thenReturn(expected);

        // when and then
        client.delete()
                .uri("/api/v1/product-price/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CashCheckDto.class)
                .value(productPrice -> productPrice.getDeleted(), equalTo(true));
    }

    private ProductPriceInfoDto createProductPriceInfoDto(long id) {
        return new ProductPriceInfoDto()
                .setId(id)
                .setShopId(1L)
                .setShopName("test_shop_name")
                .setProductId(id)
                .setProductName("test_product_" + id)
                .setPrice(100)
                .setPriceDate(NOW)
                .setDeleted(false);
    }

    private AddProductPriceDto createAddProductPriceDto(long id) {
        return new AddProductPriceDto()
                .setShopId(1L)
                .setProductId(id)
                .setPrice(100)
                .setPriceDate(NOW);
    }

    private ProductPriceDto createProductPriceDto(long id) {
        return new ProductPriceDto()
                .setId(id)
                .setShopId(1L)
                .setProductId(id)
                .setPrice(100)
                .setPriceDate(NOW)
                .setDeleted(false);
    }
}