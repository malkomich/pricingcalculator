package com.bcncgroup.pricingcalculator.adapter.primary;

import com.bcncgroup.pricingcalculator.domain.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseMapperTest {

  @Test
  void mapSuccessful() {

    var price = Mockito.mock(Price.class);
    var now = LocalDateTime.now();
    Mockito.when(price.getPriceList()).thenReturn(5);
    Mockito.when(price.getStartDate()).thenReturn(now);
    Mockito.when(price.getPrice()).thenReturn(BigDecimal.TEN);

    var priceResponse = PriceResponseMapper.toApiResponse(price);

    Assertions.assertNotNull(price);
    Assertions.assertEquals(5, priceResponse.getPriceList());
    Assertions.assertEquals(now, priceResponse.getStartDate());
    Assertions.assertEquals(BigDecimal.TEN, priceResponse.getPrice());
  }

  @Test
  void mapNull() {

    var priceResponse = PriceResponseMapper.toApiResponse(null);

    Assertions.assertNull(priceResponse);
  }

}
