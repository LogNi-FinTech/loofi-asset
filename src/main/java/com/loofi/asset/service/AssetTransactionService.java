package com.loofi.asset.service;

import com.loofi.asset.entities.Account;
import com.loofi.asset.entities.CurrentPrice;
import com.loofi.asset.entities.RealiseGain;
import com.loofi.asset.entities.Transaction;
import com.loofi.asset.exceptions.AssetBusinessException;
import com.loofi.asset.models.AssetSellReq;
import com.loofi.asset.models.PurchaseAssetReq;
import com.loofi.asset.models.enums.TransactionStatus;
import com.loofi.asset.models.enums.TransactionType;
import com.loofi.asset.repository.AccountRepository;
import com.loofi.asset.repository.AssetRepository;
import com.loofi.asset.repository.CurrentPriceRepository;
import com.loofi.asset.repository.RealiseGainRepository;
import com.loofi.asset.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@AllArgsConstructor
public class AssetTransactionService {

    private final AccountRepository accountRepository;
    private final AssetRepository assetRepository;
    private final CurrentPriceRepository currentPriceRepository;
    private final TransactionRepository transactionRepository;
    private final RealiseGainRepository realiseGainRepository;

    public void buyAsset(PurchaseAssetReq purchaseAssetReq) {
        Account account = this.accountRepository.findByUserIdAndAssetId(
                purchaseAssetReq.getUserId(),
                purchaseAssetReq.getAssetId()
        );
        if (account.getAsset().getEnableBuy() == null || !account.getAsset().getEnableBuy()) {
            throw new AssetBusinessException(HttpStatus.BAD_REQUEST, "400", "Asset is not permitted to buy");
        }
        CurrentPrice currentPrice = this.currentPriceRepository.findByAssetId(purchaseAssetReq.getAssetId());
        if (currentPrice == null) {
            throw new AssetBusinessException(HttpStatus.BAD_REQUEST, "400", "Asset price not found");
        }
        Transaction transaction = Transaction.builder()
                .createdDate(Instant.now())
                .asset(account.getAsset())
                .account(account)
                .quantity(purchaseAssetReq.getQuantity())
                .unitPrice(currentPrice.getSpotPrice())
                .totalPrice(purchaseAssetReq.getQuantity().multiply(currentPrice.getSpotPrice()))
                .transactionType(TransactionType.BUY)
                .status(TransactionStatus.PENDING)
                .build();
        this.transactionRepository.save(transaction);
    }

    public void sellAsset(AssetSellReq assetSellReq) {
        Account account = this.accountRepository.findByUserIdAndAssetId(
                assetSellReq.getUserId(),
                assetSellReq.getAssetId()
        );
        if (account.getAsset().getEnableSell() == null || !account.getAsset().getEnableSell()) {
            throw new AssetBusinessException(HttpStatus.BAD_REQUEST, "400", "Asset is not permitted to sell");
        }
        CurrentPrice currentPrice = this.currentPriceRepository.findByAssetId(assetSellReq.getAssetId());
        if (currentPrice == null) {
            throw new AssetBusinessException(HttpStatus.BAD_REQUEST, "400", "Asset price not found");
        }
        Transaction transaction = Transaction.builder()
                .createdDate(Instant.now())
                .asset(account.getAsset())
                .account(account)
                .quantity(assetSellReq.getQuantity())
                .unitPrice(currentPrice.getSpotPrice())
                .totalPrice(assetSellReq.getQuantity().multiply(currentPrice.getSpotPrice()))
                .transactionType(TransactionType.SELL)
                .status(TransactionStatus.PENDING)
                .build();
        this.transactionRepository.save(transaction);
        RealiseGain realiseGain = RealiseGain.builder()
                .createdDate(Instant.now())
                .asset(account.getAsset())
                .account(account)
                .transaction(transaction)
                .transactionType(TransactionType.SELL)
                .realisedGain(assetSellReq.getQuantity().multiply(currentPrice.getSpotPrice()))
                .build();
        this.realiseGainRepository.save(realiseGain);
    }
}
