package com.bcncgroup.pricingcalculator.adapter.config;

import com.bcncgroup.pricingcalculator.adapter.primary.PriceController;
import com.bcncgroup.pricingcalculator.application.port.primary.PriceCalculationUseCase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
class PriceControllerConfig {

  @Bean
  public PriceController priceController(PriceCalculationUseCase priceCalculationUseCase) {
    return new PriceController(priceCalculationUseCase);
  }
}
