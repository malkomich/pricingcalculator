package com.bcncgroup.pricingcalculator.application.port.primary;

import com.bcncgroup.pricingcalculator.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceCalculationUseCase {

  Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime date);
}
