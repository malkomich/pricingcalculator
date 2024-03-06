package com.bcncgroup.pricingcalculator.adapter.secondary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@IdClass(PriceEntityId.class)
@Getter
@Setter
public class PriceEntity {

  @Id
  @Column(nullable = false)
  private Integer brandId;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime startDate;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime endDate;

  @Id
  @Column(nullable = false)
  private Integer priceList;

  @Id
  @Column(nullable = false)
  private Integer productId;

  @Column(nullable = false)
  private Integer priority;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private String curr;

}
