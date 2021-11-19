package com.vorotof.advancereport.service;

import com.vorotof.advancereport.service.dto.cashier.AddCashierDto;
import com.vorotof.advancereport.service.dto.cashier.CashierDto;
import com.vorotof.advancereport.service.dto.check.AddCashCheckDto;
import com.vorotof.advancereport.service.dto.check.CashCheckDto;
import com.vorotof.advancereport.service.dto.checkline.AddCashCheckLineDto;
import com.vorotof.advancereport.service.dto.organization.AddOrganizationDto;
import com.vorotof.advancereport.service.dto.organization.OrganizationDto;
import com.vorotof.advancereport.service.dto.product.AddProductDto;
import com.vorotof.advancereport.service.dto.product.ProductDto;
import com.vorotof.advancereport.service.dto.productprice.AddProductPriceDto;
import com.vorotof.advancereport.service.dto.productprice.ProductPriceDto;
import com.vorotof.advancereport.service.dto.shop.AddShopDto;
import com.vorotof.advancereport.service.dto.shop.ShopDto;
import com.vorotof.advancereport.service.dto.telegram.TelegramFileProcessResult;
import com.vorotof.advancereport.service.format.DateTimeService;
import com.vorotof.advancereport.service.mapper.checkline.ItemToAddCheckLineDto;
import com.vorotof.advancereport.service.mapper.telegram.CashCheckDtoToTelegramFileProcessResult;
import com.vorotof.advancereport.service.ofd.Item;
import com.vorotof.advancereport.service.ofd.OfdCheck;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfdCheckServiceImpl implements OfdCheckService {

    private static final String UNDEFINED_CASHIER = "Не указан";

    private final OrganizationService organizationService;

    private final ShopService shopService;

    private final CashierService cashierService;

    private final ProductService productService;

    private final CheckService checkService;

    private final DateTimeService dateTimeService;

    private final ProductPriceService productPriceService;

    private final ItemToAddCheckLineDto itemToAddCheckLine;

    private final CashCheckDtoToTelegramFileProcessResult cashCheckDtoToTelegramFileProcessResultMapper;

    @Override
    public TelegramFileProcessResult process(OfdCheck ofdCheck, Long telegramUserId) {
        var organization = saveOrganization(ofdCheck);
        var shop = saveShop(ofdCheck, organization);
        var cashier = saveCashier(ofdCheck, shop);
        var products = saveProducts(ofdCheck, shop);
        return toResult(saveCheck(cashier, products, ofdCheck, telegramUserId));
    }

    private TelegramFileProcessResult toResult(CashCheckDto check) {
        return cashCheckDtoToTelegramFileProcessResultMapper.map(check);
    }

    /**
     * @param cashier
     * @param checkLines
     * @param ofdCheck
     * @return
     */
    private CashCheckDto saveCheck(CashierDto cashier, List<AddCashCheckLineDto> checkLines,
                                   OfdCheck ofdCheck, Long telegramUserId) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
        var checkDto = new AddCashCheckDto()// TODO в mapper
                .setCashierId(cashier.getId())
                .setRequestNumber(ofdCheck.getRequestNumber())
                .setShiftNumber(ofdCheck.getShiftNumber())
                .setKktRegId(ofdCheck.getKktRegId())
                .setFiscalDocumentNumber(ofdCheck.getFiscalDocumentNumber())
                .setFiscalDriveNumber(ofdCheck.getFiscalDriveNumber())
                .setTotalSum(ofdCheck.getTotalSum())
                .setCashSum(ofdCheck.getCashTotalSum())
                .setEcashSum(ofdCheck.getEcashTotalSum())
                .setCheckDate(dateTime)
                .setCheckLines(checkLines)
                .setTelegramUserId(telegramUserId);
        return checkService.save(checkDto);
    }

    /**
     * Сохранить организацию.
     *
     * @param ofdCheck чек ОФД
     * @return организация
     */
    private OrganizationDto saveOrganization(OfdCheck ofdCheck) {
        var organization = organizationService.findByInn(ofdCheck.getUserInn());
        if (organization == null) {
            var name = ofdCheck.getUser();
            var inn = ofdCheck.getUserInn();
            if (name == null) {
                name = inn;
            }
            var dto = new AddOrganizationDto().setName(name).setInn(inn);
            organization = organizationService.save(dto);
        }
        return organization;
    }

    /**
     * Сохранить магазин.
     *
     * @param ofdCheck     чек ОФД
     * @param organization организация
     * @return магазин
     */
    private ShopDto saveShop(OfdCheck ofdCheck, OrganizationDto organization) {
        ShopDto shop;
        if (ofdCheck.getRetailPlaceAddress() == null) {
            shop = shopService.findByName(ofdCheck.getUser());
        } else {
            shop = shopService.findByAddress(ofdCheck.getRetailPlaceAddress());
        }
        if (shop == null) {
            var name = organization.getName();
            var address = ofdCheck.getRetailPlaceAddress();
            var dto = new AddShopDto()
                    .setName(name)
                    .setAddress(address)
                    .setOrganizationId(organization.getId());
            shop = shopService.save(dto);
        }
        return shop;
    }

    /**
     * Сохранить кассира
     *
     * @param ofdCheck чек ОФД
     * @param shop     магазин
     * @return кассир
     */
    private CashierDto saveCashier(OfdCheck ofdCheck, ShopDto shop) {
        var cashier = cashierService.findByName(ofdCheck.getOperator());
        if (cashier == null) {
            var name = ofdCheck.getOperator();
            if (StringUtils.isEmpty(name)){
                name = UNDEFINED_CASHIER;
            }
            var dto = new AddCashierDto()
                    .setName(name)
                    .setShopId(shop.getId());
            cashier = cashierService.save(dto);
        }
        return cashier;
    }

    /**
     * Сохранить товары.
     *
     * @param ofdCheck чек ОФД
     * @param shop     магазин
     * @return товар
     */
    private List<AddCashCheckLineDto> saveProducts(OfdCheck ofdCheck, ShopDto shop) {
        var dateTime = fromEpoch(ofdCheck.getDateTime());
        return ofdCheck.getItems().stream().map(item -> {
            var product = productService.findByName(item.getName());
            if (product == null) {
                var dto = new AddProductDto().setName(item.getName());
                product = productService.save(dto);
            }
            savePrice(shop, product, item, dateTime);
            return itemToAddCheckLine.map(item).setProductId(product.getId());
        }).collect(Collectors.toList());
    }

    /**
     * Сохранить цену.
     * TODO самый уродский метод
     *
     * @param shop     магазин
     * @param product  товар
     * @param item     строка
     * @param dateTime дата-время
     * @return цена
     */
    private ProductPriceDto savePrice(ShopDto shop, ProductDto product, Item item, LocalDateTime dateTime) {
        var price = productPriceService.findPrice(product, shop, dateTime);
        if (price == null) {
            var dto = new AddProductPriceDto()
                    .setProductId(product.getId())
                    .setShopId(shop.getId())
                    .setPrice(item.getPrice())
                    .setPriceDate(dateTime);
            price = productPriceService.save(dto);
        }
        return price;
    }

    private LocalDateTime fromEpoch(int datetime) {
        return dateTimeService.fromEpoch(datetime);
    }

}
