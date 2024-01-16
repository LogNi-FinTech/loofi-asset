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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "asset_realise_gains")
public class RealiseGain extends LoofiAbstractAuditingEntity {

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "transaction_id")
  private Transaction transaction;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type")
  private TransactionType transactionType = TransactionType.SELL;

  @Column(name = "realised_gain")
  private BigDecimal realisedGain = BigDecimal.ZERO;
}
