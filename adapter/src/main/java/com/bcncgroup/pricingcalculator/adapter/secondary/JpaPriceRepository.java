package com.bcncgroup.pricingcalculator.adapter.secondary;

import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntity;
import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

interface JpaPriceRepository extends JpaRepository<PriceEntity, PriceEntityId> {

  @Query(
      value =

          "SELECT * FROM price p " +
          "WHERE p.brand_id = ?1 AND p.product_id = ?2 AND ?3 BETWEEN p.start_date AND p.end_date " +
          "ORDER BY p.priority DESC " +
          "LIMIT 1",
      nativeQuery = true)
  PriceEntity findPriceByDate(Integer brandId, Integer productId, LocalDateTime date);

}
