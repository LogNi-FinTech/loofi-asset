package com.loofi.asset.controllers;

import com.loofi.asset.models.PurchaseAssetReq;
import com.loofi.asset.models.PurchaseAssetResp;
import com.loofi.asset.service.AssetTransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Validated
@AllArgsConstructor
public class AssetTransactionController {

    private final AssetTransactionService assetTransactionService;

    @PostMapping("/buy")
    public void buyAsset(@Valid @RequestBody PurchaseAssetReq purchaseAssetReq) {
        assetTransactionService.buyAsset(purchaseAssetReq);
    }
}
