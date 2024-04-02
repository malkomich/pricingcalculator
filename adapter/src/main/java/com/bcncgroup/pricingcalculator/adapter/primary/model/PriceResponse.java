package com.bcncgroup.pricingcalculator.adapter.primary.model;

import com.bcncgroup.pricingcalculator.adapter.primary.PriceSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponse {

  private Integer brandId;

  @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Integer priceList;

  private Integer productId;

  @JsonSerialize(using = PriceSerializer.class)
  private BigDecimal price;

  private String currency;

}
