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
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "investor_asset_states")
public class AssetState extends LoofiAbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;


  @ManyToOne
  @JoinColumn(name = "asset_account_id")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "transaction_id")
  private Transaction transaction;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type")
  private TransactionType transactionType;


  @Column(name = "initial_quantity")
  private BigDecimal initialQuantity = BigDecimal.ZERO;

  @Column(name = "quantity")
  private BigDecimal quantity = BigDecimal.ZERO; // current quantity

  @Column(name = "unit_price")
  private BigDecimal unitPrice;
}
