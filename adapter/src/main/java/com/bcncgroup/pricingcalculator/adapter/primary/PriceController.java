package com.bcncgroup.pricingcalculator.adapter.primary;

import com.bcncgroup.pricingcalculator.adapter.primary.model.PriceResponse;
import com.bcncgroup.pricingcalculator.application.port.primary.PriceCalculationUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(PriceController.PRICE_PATH)
@Tag(name = "Price Controller", description = "Pricing Calculation API")
public class PriceController {

  public static final String PRICE_PATH = "/price";
  public static final String BRAND_ID_PARAM = "brandId";
  public static final String PRODUCT_ID_PARAM = "productId";
  public static final String DATE_PARAM = "date";
  public static final String DATETIME_PATTERN = "yyyy-MM-dd-HH.mm.ss";

  private final PriceCalculationUseCase priceCalculationUseCase;

  @Operation(
      summary = "Get the price for a product given the date",
      description = "Retrieve the final price for a product, applied in a specific date. The response includes " +
          "the product and brand identifiers, the price rate, price and the dates between that price is applied.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(
          implementation = PriceResponse.class),
          mediaType = "application/json") }),
      @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @GetMapping
  public ResponseEntity<PriceResponse> getPrice(
      @Parameter(description  = "Brand identifier (1 = ZARA)", example = "1", required = true)
      @RequestParam(BRAND_ID_PARAM) Integer brandId,
      @Parameter(description  = "Product identifier", example = "35455", required = true)
      @RequestParam(PRODUCT_ID_PARAM) Integer productId,
      @Parameter(description  = "Date for the price to be applied. Format: " + DATETIME_PATTERN,
          example = "2020-07-14-00.00.01", required = true)
      @RequestParam(DATE_PARAM) @DateTimeFormat(pattern = DATETIME_PATTERN) LocalDateTime date) {

    return priceCalculationUseCase.getPrice(brandId, productId, date)
        .map(PriceResponseMapper::toApiResponse)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
