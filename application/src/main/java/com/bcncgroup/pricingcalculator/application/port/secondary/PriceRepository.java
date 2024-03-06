package com.bcncgroup.pricingcalculator.application.port.secondary;

import com.bcncgroup.pricingcalculator.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

  Optional<Price> findByDate(Integer brandId, Integer productId, LocalDateTime date);

}
