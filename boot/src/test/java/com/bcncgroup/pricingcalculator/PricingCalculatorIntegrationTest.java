package com.bcncgroup.pricingcalculator;

import com.bcncgroup.pricingcalculator.adapter.primary.PriceController;
import com.bcncgroup.pricingcalculator.adapter.primary.model.PriceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest(classes = PricingcalculatorApplication.class)
@AutoConfigureMockMvc
public class PricingCalculatorIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void day14At10AM() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-06-14-10.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(mvcResult -> {
          final String json = mvcResult.getResponse().getContentAsString();
          final PriceResponse priceResponse = deserialize(json, PriceResponse.class);

          Assertions.assertEquals(BigDecimal.valueOf(35.50).setScale(2), priceResponse.getPrice());
        });
  }

  @Test
  void day14At4PM() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-06-14-16.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(mvcResult -> {
          final String json = mvcResult.getResponse().getContentAsString();
          final PriceResponse priceResponse = deserialize(json, PriceResponse.class);

          Assertions.assertEquals(BigDecimal.valueOf(25.45).setScale(2), priceResponse.getPrice());
        });
  }

  @Test
  void day14At9PM() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-06-14-21.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(mvcResult -> {
          final String json = mvcResult.getResponse().getContentAsString();
          final PriceResponse priceResponse = deserialize(json, PriceResponse.class);

          Assertions.assertEquals(BigDecimal.valueOf(35.50).setScale(2), priceResponse.getPrice());
        });
  }

  @Test
  void day15At10AM() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-06-15-10.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(mvcResult -> {
          final String json = mvcResult.getResponse().getContentAsString();
          final PriceResponse priceResponse = deserialize(json, PriceResponse.class);

          Assertions.assertEquals(BigDecimal.valueOf(30.50).setScale(2), priceResponse.getPrice());
        });
  }

  @Test
  void day16At21AM() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-06-16-21.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(mvcResult -> {
          final String json = mvcResult.getResponse().getContentAsString();
          final PriceResponse priceResponse = deserialize(json, PriceResponse.class);

          Assertions.assertEquals(BigDecimal.valueOf(38.95).setScale(2), priceResponse.getPrice());
        });
  }

  private static <T> T deserialize(String json, Class<T> objectClass) throws IOException {

    final ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.registerModule(new JavaTimeModule());

    return mapper.readValue(json, objectClass);
  }

}
