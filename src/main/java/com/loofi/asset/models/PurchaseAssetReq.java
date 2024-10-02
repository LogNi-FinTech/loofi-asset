package com.loofi.asset.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseAssetReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long assetId;
    private BigDecimal quantity;
}
