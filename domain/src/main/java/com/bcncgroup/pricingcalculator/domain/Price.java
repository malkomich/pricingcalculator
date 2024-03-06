package com.bcncgroup.pricingcalculator.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Price {

  private final Integer brandId;

  private final LocalDateTime startDate;

  private final LocalDateTime endDate;

  private final Integer priceList;

  private final Integer productId;

  private final Integer priority;

  private final BigDecimal price;

  private final Currency currency;

}
