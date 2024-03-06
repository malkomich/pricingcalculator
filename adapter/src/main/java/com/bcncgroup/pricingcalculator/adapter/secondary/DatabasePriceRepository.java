package com.bcncgroup.pricingcalculator.adapter.secondary;

import com.bcncgroup.pricingcalculator.adapter.secondary.entity.PriceEntity;
import com.bcncgroup.pricingcalculator.application.port.secondary.PriceRepository;
import com.bcncgroup.pricingcalculator.domain.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DatabasePriceRepository implements PriceRepository {

  private final JpaPriceRepository jpaPriceRepository;

  @Override
  public Optional<Price> findByDate(Integer brandId, Integer productId, LocalDateTime date) {

    final PriceEntity priceEntity = jpaPriceRepository.findPriceByDate(brandId, productId, date);

    if (Objects.nonNull(priceEntity)) {
      return Optional.of(PriceEntityMapper.toDomain(priceEntity));
    }

    return Optional.empty();
  }

}
