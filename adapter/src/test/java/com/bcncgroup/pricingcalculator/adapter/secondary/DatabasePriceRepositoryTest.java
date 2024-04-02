package com.bcncgroup.pricingcalculator.adapter.secondary;

import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class DatabasePriceRepositoryTest {

  @InjectMocks
  private DatabasePriceRepository databasePriceRepository;

  @Mock
  private JpaPriceRepository jpaPriceRepository;

  private AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
  }

  @Test
  void expectedResult () {

    var now = LocalDateTime.now();
    var priceEntity = Mockito.mock(PriceEntity.class);

    Mockito.when(jpaPriceRepository.findPriceByDate(1, 35455, now)).thenReturn(priceEntity);
    Mockito.when(priceEntity.getPrice()).thenReturn(BigDecimal.TEN);

    var result = databasePriceRepository.findByDate(1, 35455, now);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(BigDecimal.TEN, result.get().getPrice());
  }

  @Test
  void emptyResult () {

    var now = LocalDateTime.now();

    Mockito.when(jpaPriceRepository.findPriceByDate(1, 35455, now)).thenReturn(null);

    var result = databasePriceRepository.findByDate(1, 35455, now);

    Assertions.assertTrue(result.isEmpty());
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

}
