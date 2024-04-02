package com.bcncgroup.pricingcalculator.adapter.primary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;

class PriceSerializerTest {

  private PriceSerializer serializer = new PriceSerializer();

  @Mock
  private JsonGenerator jsonGenerator;

  @Mock
  private SerializerProvider serializerProvider;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void serializeMultipleDecimals() throws IOException {

    var value = new BigDecimal("12.3456");

    serializer.serialize(value, jsonGenerator, serializerProvider);

    Mockito.verify(jsonGenerator).writeString("12.35");
  }

  @Test
  void serializeTwoDecimals() throws IOException {

    var value = new BigDecimal("10.50");

    serializer.serialize(value, jsonGenerator, serializerProvider);

    Mockito.verify(jsonGenerator).writeString("10.50");
  }

  @Test
  void serializeNoDecimals() throws IOException {

    var value = new BigDecimal("8");

    serializer.serialize(value, jsonGenerator, serializerProvider);

    Mockito.verify(jsonGenerator).writeString("8.00");
  }
}
