package com.loofi.asset.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class PurchaseAssetResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String status;
}
