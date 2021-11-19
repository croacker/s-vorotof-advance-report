package com.vorotof.advancereport.service.mapper.telegram;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.domain.ProductPriceView;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.service.dto.telegram.TelegramProductPriceDto;
import com.vorotof.advancereport.service.format.NumberService;
import com.vorotof.advancereport.service.format.NumberServiceImpl;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceToTelegramDtoTest {

    private NumberService numberService;

    private ProductPriceToTelegramDto mapper;

    private TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup() {
        numberService = new NumberServiceImpl();
        mapper = new ProductPriceToTelegramDto(numberService);
    }

    @Test
    void shouldMapEntity() {
        //given
        var given = createEntity(0L);
        var expected = createDto(0L);

        // when
        var actual = mapper.map(given);

        // then
        assertTrue(new ReflectionEquals(expected).matches(actual),
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private ProductPriceView createEntity(long id) {
        return testEntitiesProducer.createProductPriceView(id);
    }

    private TelegramProductPriceDto createDto(long id) {
        return testEntitiesProducer.createTelegramProductPriceDto(id);
    }
}