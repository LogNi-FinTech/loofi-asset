package com.loofi.asset.entities;

import com.loofi.asset.models.enums.TransactionType;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_trading_rules")
public class AssetTradingRule extends LoofiAbstractAuditingEntity {

  @Column(name = "fixed_fee")
  private BigDecimal fixedFee = BigDecimal.ZERO;

  @Column(name = "fee_rate")
  private BigDecimal feeRate;

  @Column(name = "tax_rate")
  private BigDecimal taxRate;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private TransactionType type;

  @Column(name = "min_buy_amount")
  private BigDecimal minBuyAmount;

  @Column(name = "max_buy_amount")
  private BigDecimal maxBuyAmount;

  @Column(name = "min_sell_amount")
  private BigDecimal minSellAmount;

  @Column(name = "max_sell_amount")
  private BigDecimal maxSellAmount;

  @Column(name = "daily_max_sell_amount")
  private BigDecimal dailyMaxSellAmount;

  @Column(name = "daily_max_buy_amount")
  private BigDecimal dailyMaxBuyAmount;

}
