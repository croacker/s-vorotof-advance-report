package com.vorotof.advancereport.service.mapper.product;


import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class DtoToProductTest {

    private DtoToProduct mapper;

    @BeforeEach
    void setup() {
        mapper = new DtoToProduct();
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

    private Product createEntity() {
        return new Product()
                .setId(0L)
                .setName("test_product")
                .setDeleted(false);
    }

    private ProductDto createDto() {
        return new ProductDto()
                .setId(0L)
                .setName("test_product")
                .setDeleted(false);
    }

}
