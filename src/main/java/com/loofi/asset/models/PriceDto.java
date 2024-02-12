package com.loofi.asset.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class PriceDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private Long assetId;
    private AssetDTO asset;
    private BigDecimal spotPrice;
    private BigDecimal weightedAvgBasis;
    private BigDecimal midPrice;
    private BigDecimal buyBackPrice;
    private BigDecimal sellPrice;
    private BigDecimal spread;
}
