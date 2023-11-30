package com.loofi.asset.entities;


import java.math.BigDecimal;
import java.time.Instant;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asset_accounts")
public class Account extends LoofiAbstractAuditingEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Asset_AccountGenerator")
  @SequenceGenerator(name = "Asset_AccountGenerator", sequenceName = "ASSET_ACCOUNT_ID_GEN")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "asset_id")
  private Asset asset;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "asset_balance")
  private BigDecimal balance;

  @Column(name = "first_order_date")
  private Instant firstOrderDate;
}
