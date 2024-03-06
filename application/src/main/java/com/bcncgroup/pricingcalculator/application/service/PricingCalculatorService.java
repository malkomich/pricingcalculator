package com.bcncgroup.pricingcalculator.application.service;

import com.bcncgroup.pricingcalculator.application.port.primary.PriceCalculationUseCase;
import com.bcncgroup.pricingcalculator.application.port.secondary.PriceRepository;
import com.bcncgroup.pricingcalculator.domain.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PricingCalculatorService implements PriceCalculationUseCase {

  private final PriceRepository priceRepository;

  @Override
  public Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime date) {

    return priceRepository.findByDate(brandId, productId, date);
  }
}
