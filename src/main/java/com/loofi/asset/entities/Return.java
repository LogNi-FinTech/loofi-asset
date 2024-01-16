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
@Table(name = "asset_returns")
public class Return extends LoofiAbstractAuditingEntity {

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @ManyToOne
  @JoinColumn(name = "asset_account_id")
  private Account account;

  @Column(name = "total_quantity")
  private BigDecimal totalQuantity;

  @Column(name = "weighted_cost")
  private BigDecimal weightedCost;

  @Column(name = "realised_gain")
  private BigDecimal realisedGain;
}
