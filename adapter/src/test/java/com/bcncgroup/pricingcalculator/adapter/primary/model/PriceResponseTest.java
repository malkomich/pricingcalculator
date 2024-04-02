package com.bcncgroup.pricingcalculator.adapter.primary.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class PriceResponseTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

  @BeforeAll
  static void setup() {

    var timeModule = new JavaTimeModule()
        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER));

    MAPPER.registerModule(timeModule);
  }

  @Test
  void seralizesToJSON() throws Exception {

    var priceResponse = new PriceResponse();
    priceResponse.setBrandId(1);
    priceResponse.setStartDate(LocalDateTime.parse("2022-01-05-12.30.00", DATETIME_FORMATTER));
    priceResponse.setEndDate(LocalDateTime.parse("2024-06-10-12.30.00", DATETIME_FORMATTER));
    priceResponse.setPriceList(2);
    priceResponse.setProductId(10);
    priceResponse.setPrice(BigDecimal.valueOf(12.30).setScale(2, RoundingMode.HALF_UP));
    priceResponse.setCurrency("GBP");

    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(
            getClass().getResource("/fixtures/priceResponse.json"),
            PriceResponse.class));

    Assertions.assertEquals(expected, MAPPER.writeValueAsString(priceResponse));
  }
}
