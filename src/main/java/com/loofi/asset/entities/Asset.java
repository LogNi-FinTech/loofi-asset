package com.loofi.asset.entities;

import com.loofi.asset.models.enums.AssetType;


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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "assets")
public class Asset extends LoofiAbstractAuditingEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;


  @Column(name = "tax_percentage")
  private BigDecimal taxPercentage;

  @Column(name = "premium_adjustment")
  private BigDecimal premiumAdjustment;

  @Enumerated(EnumType.STRING)
  @Column(name = "asset_type")
  private AssetType type;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "attributes", columnDefinition = "jsonb")
  private String attributes; // asset attributes, all attributes regarding asset

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "icons", columnDefinition = "jsonb")
  private String icons;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "transaction_limits", columnDefinition = "jsonb")
  private TransactionLimit transactionLimit;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "holdings", columnDefinition = "jsonb")
  private String holdings; // asset company info

  @Column(name = "enable_buy")
  private Boolean enableBuy = Boolean.TRUE;

  @Column(name = "enable_sell")
  private Boolean enableSell = Boolean.TRUE;

}
