package com.loofi.asset.models;

import com.loofi.asset.entities.TransactionLimit;
import com.loofi.asset.models.enums.AssetType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class AssetDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String name;
    private String description;
    private BigDecimal taxPercentage;
    private BigDecimal premiumAdjustment;
    private AssetType type;
    private String attributes; // asset attributes, all attributes regarding asset
    private String icons;
    private TransactionLimit transactionLimit;
    private String holdings; // asset company info
    private Boolean enableBuy;
    private Boolean enableSell;
}
