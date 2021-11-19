package com.vorotof.advancereport.service;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.domain.ProductPrice;
import com.vorotof.advancereport.domain.Shop;
import com.vorotof.advancereport.repo.ProductPriceRepo;
import com.vorotof.advancereport.repo.ProductPriceViewRepo;
import com.vorotof.advancereport.repo.ProductRepo;
import com.vorotof.advancereport.repo.ShopRepo;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceInfoDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.format.NumberService;
import com.vorotof.advancereport.service.format.NumberServiceImpl;
import com.vorotof.advancereport.service.mapper.productprice.AddDtoToProductPrice;
import com.vorotof.advancereport.service.mapper.productprice.DtoToProductPrice;
import com.vorotof.advancereport.service.mapper.productprice.ProductPriceToDto;
import com.vorotof.advancereport.service.mapper.productprice.ProductPriceToInfoDto;
import com.vorotof.advancereport.service.mapper.telegram.ProductPriceToTelegramDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductPriceServiceImplTest {

    public static final LocalDateTime NOW = TestEntitiesProducer.NOW;

    private ProductPriceServiceImpl service;

    @MockBean
    private ProductPriceRepo repo;

    @MockBean
    private ProductPriceViewRepo viewRepo;

    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private ShopRepo shopRepo;

    private NumberService numberService;

    private ProductPriceToDto toDtoMapper;

    private ProductPriceToInfoDto toInfoDtoMapper;

    private DtoToProductPrice toEntityMapper;

    private AddDtoToProductPrice addToEntityMapper;

    private ProductPriceToTelegramDto toTelegramDtoMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        numberService = new NumberServiceImpl();
        toDtoMapper = new ProductPriceToDto();
        toInfoDtoMapper = new ProductPriceToInfoDto();
        toEntityMapper = new DtoToProductPrice();
        addToEntityMapper = new AddDtoToProductPrice();
        toTelegramDtoMapper = new ProductPriceToTelegramDto(numberService);
        service = new ProductPriceServiceImpl(repo, viewRepo, productRepo, shopRepo, toDtoMapper, toInfoDtoMapper,
                toEntityMapper, addToEntityMapper, toTelegramDtoMapper);
    }


    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createEntitiesList());
        var expected = createInfoDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
        // given
        when(repo.findById(0L)).thenReturn(Optional.of(createEntity(0L)));
        var expected = createInfoDto(0L);

        // when
        var actual = service.findOne(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findByProduct() {
        // given
        var product = createProduct(0L);
        when(productRepo.findById(0L)).thenReturn(Optional.of(product));
        when(repo.findByProduct(product)).thenReturn(createEntitiesList());
        var expected = createInfoDtosList();

        // when
        var actual = service.findByProduct(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findPrice() {
        // given
        var productDto = createProductDto(0L);
        var product = createProduct(0L);
        var shopDto = createShopDto(0L);
        var shop = createShop(0L);
        when(productRepo.findById(0L)).thenReturn(Optional.of(product));
        when(shopRepo.findById(0L)).thenReturn(Optional.of(shop));
        when(repo.findByProductAndShopAndPriceDate(product, shop, NOW)).thenReturn(Optional.of(createEntity(0L)));
        var expected = createDto(0L);

        // when
        var actual = service.findPrice(productDto, shopDto, NOW);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddDto(0L);
        var shop = createShop(0L);
        var product = createProduct(0L);
        when(shopRepo.findById(0L)).thenReturn(Optional.of(shop));
        when(productRepo.findById(0L)).thenReturn(Optional.of(product));
        when(repo.save(any())).thenReturn(createEntity(0L));
        var expected = createDto(0L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update() {
        // given
        var given = createDto(0L);
        when(repo.save(any())).thenReturn(createEntity(0L));
        var expected = createDto(0L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete() {
        // given
        var entity = createEntity(0L);
        when(repo.findById(0L)).thenReturn(Optional.of(entity));
        when(repo.save(any())).thenReturn(entity);
        var expected = createDto(0L).setDeleted(true);

        // when
        var actual = service.delete(0L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void getProductsPrices() {
        // given
        var productName = "test_product_";
        var pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(productRepo.findByNameContainingIgnoreCase(productName, pageable)).thenReturn(createProductsList());
//        when(repo.findByProduct(any())).thenReturn()

        var expected = createDtosList();

        // when
        var actual = service.getProductsPrices(productName, pageable);

        // then
        // TODO
    }

    private List<ProductPrice> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private ProductPrice createEntity(long id) {
        return testEntitiesProducer.createProductPrice(id);
    }

    private List<ProductPriceDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private ProductPriceDto createDto(long id) {
        return testEntitiesProducer.createProductPriceDto(id);
    }

    private AddProductPriceDto createAddDto(long id) {
        return testEntitiesProducer.createAddProductPriceDto(id);
    }

    private List<ProductPriceInfoDto> createInfoDtosList() {
        return Arrays.asList(
                createInfoDto(1L),
                createInfoDto(2L),
                createInfoDto(3L),
                createInfoDto(4L),
                createInfoDto(5L)
        );
    }

    private ProductPriceInfoDto createInfoDto(long id) {
        return testEntitiesProducer.createProductPriceInfoDto(id);
    }

    private Product createProduct(long id) {
        return testEntitiesProducer.createProduct(id);
    }

    private List<Product> createProductsList() {
        return Arrays.asList(
                createProduct(0L),
                createProduct(1L),
                createProduct(2L),
                createProduct(3L),
                createProduct(4L),
                createProduct(5L)
        );
    }


    private ProductDto createProductDto(long id) {
        return testEntitiesProducer.createProductDto(id);
    }

    private Shop createShop(long id) {
        return testEntitiesProducer.createShop(id);
    }

    private ShopDto createShopDto(long id) {
        return testEntitiesProducer.createShopDto(id);
    }

}