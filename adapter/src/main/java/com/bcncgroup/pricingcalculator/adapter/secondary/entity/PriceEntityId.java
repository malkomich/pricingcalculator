package com.bcncgroup.pricingcalculator.adapter.secondary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PriceEntityId implements Serializable {

  private Integer brandId;

  private Integer productId;

  private Integer priceList;

}
