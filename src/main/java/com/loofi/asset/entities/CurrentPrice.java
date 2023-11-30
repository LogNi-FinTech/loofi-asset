package com.loofi.asset.entities;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_current_price")
public class CurrentPrice extends LoofiAbstractAuditingEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @Column(name = "spot_price")
  private BigDecimal spotPrice;

  @Column(name = "weighted_avg_basis")
  private BigDecimal weightedAvgBasis;


  @Column(name = "mid_price")
  private BigDecimal midPrice;


  @Column(name = "buy_back_price")
  private BigDecimal buyBackPrice;

  @Column(name = "sell_price")
  private BigDecimal sellPrice;

  @Column(name = "final_spread")
  private BigDecimal spread;
}
