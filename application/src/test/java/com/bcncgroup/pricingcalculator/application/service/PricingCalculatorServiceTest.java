package com.bcncgroup.pricingcalculator.application.service;

import com.bcncgroup.pricingcalculator.application.port.secondary.PriceRepository;
import com.bcncgroup.pricingcalculator.domain.Price;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class PricingCalculatorServiceTest {

  @InjectMocks
  private PricingCalculatorService pricingCalculatorService;

  @Mock
  private PriceRepository priceRepository;

  private AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
  }

  @Test
  void expectedPrice() {

    var now = LocalDateTime.now();
    var price = Mockito.mock(Price.class);

    Mockito.when(priceRepository.findByDate(1, 35455, now)).thenReturn(Optional.of(price));

    var result = pricingCalculatorService.getPrice(1, 35455, now);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(price, result.get());
  }

  @Test
  void emptyPrice() {

    var now = LocalDateTime.now();
    var price = Mockito.mock(Price.class);

    Mockito.when(priceRepository.findByDate(1, 35455, now)).thenReturn(Optional.empty());

    var result = pricingCalculatorService.getPrice(1, 35455, now);

    Assertions.assertTrue(result.isEmpty());
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

}
