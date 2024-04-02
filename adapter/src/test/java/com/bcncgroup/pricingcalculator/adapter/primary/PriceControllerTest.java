package com.bcncgroup.pricingcalculator.adapter.primary;

import com.bcncgroup.pricingcalculator.adapter.config.WebMvcConfig;
import com.bcncgroup.pricingcalculator.adapter.primary.model.PriceResponse;
import com.bcncgroup.pricingcalculator.application.port.primary.PriceCalculationUseCase;
import com.bcncgroup.pricingcalculator.domain.Price;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcConfig
class PriceControllerTest {

  private static MockMvc mockMvc;

  @MockBean
  private PriceCalculationUseCase priceCalculationUseCase;

  @BeforeAll
  static void setup(@Autowired WebApplicationContext webApplicationContext) {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void getPriceSuccessful() throws Exception {

    var date = LocalDateTime.of(2022, 01, 10, 12, 30);
    var priceMock = Mockito.mock(Price.class);
    Mockito.when(priceMock.getPrice()).thenReturn(BigDecimal.valueOf(35.50));
    Mockito.when(priceMock.getStartDate()).thenReturn(date);

    BDDMockito
        .given(priceCalculationUseCase.getPrice(1, 35455, LocalDateTime.parse("2023-07-14T10:00:00")))
        .willReturn(Optional.of(priceMock));

    mockMvc
        .perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2023-07-14-10.00.00"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.50"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2022-01-10-12.30.00"));
  }

  @Test
  void priceNotFound() throws Exception {

    BDDMockito
        .given(priceCalculationUseCase.getPrice(1, 35455, LocalDateTime.parse("2020-07-14T10:00:00")))
        .willReturn(Optional.empty());

    mockMvc.perform(MockMvcRequestBuilders
        .get(PriceController.PRICE_PATH)
        .param(PriceController.BRAND_ID_PARAM, "1")
        .param(PriceController.PRODUCT_ID_PARAM, "35455")
        .param(PriceController.DATE_PARAM, "2020-07-14-10.00.00"))
        .andExpect(status().isNotFound());
  }

  @Test
  void wrongBrandIdFormat() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "A")
            .param(PriceController.PRODUCT_ID_PARAM, "35455")
            .param(PriceController.DATE_PARAM, "2020-07-14-10.00.00"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void wrongProductIdFormat() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "AAA")
            .param(PriceController.DATE_PARAM, "2020-07-14-10.00.00"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void wrongDateFormat() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
            .get(PriceController.PRICE_PATH)
            .param(PriceController.BRAND_ID_PARAM, "1")
            .param(PriceController.PRODUCT_ID_PARAM, "AAA")
            .param(PriceController.DATE_PARAM, "2020-07-14T10:00:00"))
        .andExpect(status().isBadRequest());
  }
}
