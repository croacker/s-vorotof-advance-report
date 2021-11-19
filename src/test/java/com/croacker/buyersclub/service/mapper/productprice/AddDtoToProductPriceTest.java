package com.vorotof.advancereport.service.mapper.productprice;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class AddDtoToProductPriceTest {

    private AddDtoToProductPrice mapper;

    private final static LocalDateTime NOW = LocalDateTime.now();

    @BeforeEach
    void setup() {
        mapper = new AddDtoToProductPrice();
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

    private ProductPrice createEntity() {
        return new ProductPrice()
                .setPrice(1)
                .setPriceDate(NOW);
    }

    private AddProductPriceDto createDto() {
        return new AddProductPriceDto()
                .setPrice(1)
                .setPriceDate(NOW);
    }

}