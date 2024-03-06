package com.bcncgroup.pricingcalculator.adapter.secondary;

import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceEntityMapperTest {

  @Test
  void mapSuccessful() {

    var priceEntity = Mockito.mock(PriceEntity.class);
    var now = LocalDateTime.now();
    Mockito.when(priceEntity.getPriceList()).thenReturn(5);
    Mockito.when(priceEntity.getStartDate()).thenReturn(now);
    Mockito.when(priceEntity.getPrice()).thenReturn(BigDecimal.TEN);

    var price = PriceEntityMapper.toDomain(priceEntity);

    Assertions.assertNotNull(price);
    Assertions.assertEquals(5, price.getPriceList());
    Assertions.assertEquals(now, price.getStartDate());
    Assertions.assertEquals(BigDecimal.TEN, price.getPrice());
  }

  @Test
  void mapNull() {

    var price = PriceEntityMapper.toDomain(null);

    Assertions.assertNull(price);
  }

}
