package com.loofi.asset.entities;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionLimit {
  @NotNull
  @Positive
  private BigDecimal minimumBuyQuantity;

  @NotNull
  @Positive
  private BigDecimal minimumSellQuantity;

  @NotNull
  @Positive
  private BigDecimal maximumBuyQuantity;

  @NotNull
  @Positive
  private BigDecimal maximumSellQuantity;

  @NotNull
  @Positive
  private BigDecimal dailyMaximumSellQuantity;

  @NotNull
  @Positive
  private BigDecimal dailyMaximumBuyQuantity;
}
