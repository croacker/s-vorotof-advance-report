package com.vorotof.advancereport.service;

import com.vorotof.advancereport.TestConfiguration;
import com.vorotof.advancereport.domain.Product;
import com.vorotof.advancereport.domain.ProductGroup;
import com.vorotof.advancereport.repo.ProductGroupRepo;
import com.vorotof.advancereport.repo.ProductRepo;
import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.mapper.product.AddDtoToProduct;
import com.vorotof.advancereport.service.mapper.product.DtoToProduct;
import com.vorotof.advancereport.service.mapper.product.ProductToDto;
import com.croacker.tests.TestEntitiesProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class ProductServiceTest {

    private ProductService service;

    @MockBean
    private ProductRepo repo;

    @MockBean
    private ProductGroupRepo productGroupRepo;

    private ProductToDto toDtoMapper;

    private DtoToProduct toEntityMapper;

    private AddDtoToProduct addToEntityMapper;

    private final TestEntitiesProducer testEntitiesProducer = new TestEntitiesProducer();

    @BeforeEach
    void setup(){
        toDtoMapper = new ProductToDto();
        toEntityMapper = new DtoToProduct();
        addToEntityMapper = new AddDtoToProduct();
        service = new ProductServiceImpl(repo, productGroupRepo, toDtoMapper, toEntityMapper, addToEntityMapper);
    }

    @Test
    void findAll() {
        // given
        var given = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt");
        when(repo.findByDeletedIsFalse(given)).thenReturn(createEntitiesList());
        var expected = createDtosList();

        // when
        var actual = service.findAll(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void findOne() {
        // given
        when(repo.findById(1L)).thenReturn(Optional.of(createEntity(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.findOne(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void save() {
        // given
        var given = createAddDto(1L);
        when(repo.save(any())).thenReturn(createEntity(1L));
        when(productGroupRepo.findById(1L)).thenReturn(Optional.of(createProductGroup(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.save(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void update() {
        // given
        var given = createDto(1L);
        when(repo.save(any())).thenReturn(createEntity(1L));
        when(productGroupRepo.findById(1L)).thenReturn(Optional.of(createProductGroup(1L)));
        var expected = createDto(1L);

        // when
        var actual = service.update(given);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    @Test
    void delete() {
        // given
        var given = createDto(1L);
        when(repo.findById(any())).thenReturn(Optional.of(createEntity(1L)));
        when(repo.save(any())).thenReturn(createEntity(1L).setDeleted(true));
        var expected = createDto(1L).setDeleted(true);

        // when
        var actual = service.delete(1L);

        // then
        assertEquals(expected, actual,
                () -> "Not equals objects. Actual: " + actual + "; expect: " + expected);
    }

    private List<Product> createEntitiesList() {
        return Arrays.asList(
                createEntity(1L),
                createEntity(2L),
                createEntity(3L),
                createEntity(4L),
                createEntity(5L)
        );
    }

    private Product createEntity(long id) {
        return testEntitiesProducer.createProduct(id);
    }

    private List<ProductDto> createDtosList() {
        return Arrays.asList(
                createDto(1L),
                createDto(2L),
                createDto(3L),
                createDto(4L),
                createDto(5L)
        );
    }

    private ProductDto createDto(long id) {
        return testEntitiesProducer.createProductDto(id);
    }

    private AddProductDto createAddDto(long id) {
        return testEntitiesProducer.createAddProductDto(id);
    }

    private ProductGroup createProductGroup(long id) {
        return testEntitiesProducer.createProductGroup(id);
    }
}