package com.bcncgroup.pricingcalculator.adapter.primary.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public
class PriceResponse {

  private Integer brandId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Integer priceList;

  private Integer productId;

  private BigDecimal price;

  private String currency;

}
