package com.loofi.asset.entities;

import com.loofi.asset.models.enums.TransactionStatus;
import com.loofi.asset.models.enums.TransactionType;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Table(name = "asset_transactions")
public class Transaction extends LoofiAbstractAuditingEntity {

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @ManyToOne
  @JoinColumn(name = "asset_account_id")
  private Account account;

  @Column(name = "quantity")
  private BigDecimal quantity;

  @Column(name = "unit_price")
  private BigDecimal unitPrice;

  @Column(name = "fee")
  private BigDecimal fee;

  @Column(name = "total_price")
  private BigDecimal totalPrice;


  @Column(name = "mid_price")
  private BigDecimal midPrice;

  @Column(name = "effective_spread")
  private BigDecimal effectiveSpread;

  @Column(name = "raw_spread")
  private BigDecimal rawSpread;

  @Column(name = "ref_id")
  private String refId;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private TransactionStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type", nullable = false)
  private TransactionType transactionType;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "info", columnDefinition = "jsonb")
  private String info;
}
