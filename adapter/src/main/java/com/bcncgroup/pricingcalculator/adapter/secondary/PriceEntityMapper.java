package com.bcncgroup.pricingcalculator.adapter.secondary;

import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntity;
import com.bcncgroup.pricingcalculator.domain.Currency;
import com.bcncgroup.pricingcalculator.domain.Price;

import java.util.Objects;

class PriceEntityMapper {

  static Price toDomain(PriceEntity priceEntity) {

    if (Objects.isNull(priceEntity)) {
      return null;
    }

    return Price.builder()
        .brandId(priceEntity.getBrandId())
        .startDate(priceEntity.getStartDate())
        .endDate(priceEntity.getEndDate())
        .priceList(priceEntity.getPriceList())
        .productId(priceEntity.getProductId())
        .priority(priceEntity.getPriority())
        .price(priceEntity.getPrice())
        .currency(mapCurrency(priceEntity.getCurr()))
        .build();
  }

  private static Currency mapCurrency(String value) {

    for(Currency curr: Currency.values()) {
      if (curr.name().equals(value)) {
        return curr;
      }
    }
    return null;
  }

}
