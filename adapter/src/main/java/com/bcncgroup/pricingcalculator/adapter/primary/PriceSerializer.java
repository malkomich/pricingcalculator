package com.bcncgroup.pricingcalculator.adapter.primary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceSerializer extends StdSerializer<BigDecimal> {

  public PriceSerializer() {
    super(BigDecimal.class);
  }

  @Override
  public void serialize(BigDecimal value, JsonGenerator generator, SerializerProvider provider) throws IOException {

    var roundedValue = value.setScale(
        2,
        RoundingMode.HALF_EVEN);

    generator.writeString(roundedValue.toString());
  }
}