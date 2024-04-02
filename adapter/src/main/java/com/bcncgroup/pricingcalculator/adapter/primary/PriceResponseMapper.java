package com.bcncgroup.pricingcalculator.adapter.primary;

import com.bcncgroup.pricingcalculator.adapter.primary.model.PriceResponse;
import com.bcncgroup.pricingcalculator.domain.Currency;
import com.bcncgroup.pricingcalculator.domain.Price;

import java.util.Objects;

class PriceResponseMapper {

  private PriceResponseMapper() {}

  static PriceResponse toApiResponse(Price price) {

    if (Objects.isNull(price)) {
      return null;
    }

    final PriceResponse priceResponse = new PriceResponse();

    priceResponse.setBrandId(price.getBrandId());
    priceResponse.setStartDate(price.getStartDate());
    priceResponse.setEndDate(price.getEndDate());
    priceResponse.setPriceList(price.getPriceList());
    priceResponse.setProductId(price.getProductId());
    priceResponse.setPrice(price.getPrice());
    priceResponse.setCurrency(mapCurrency(price.getCurrency()));

    return priceResponse;
  }

  private static String mapCurrency(Currency currency) {

    if (Objects.nonNull(currency)) {
      return currency.name();
    }
    return null;
  }

}
