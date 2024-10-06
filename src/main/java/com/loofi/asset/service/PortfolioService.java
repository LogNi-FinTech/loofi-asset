package com.loofi.asset.service;

import com.loofi.asset.entities.Account;
import com.loofi.asset.entities.CurrentPrice;
import com.loofi.asset.repository.AccountRepository;
import com.loofi.asset.repository.CurrentPriceRepository;
import com.loofi.asset.repository.RealiseGainRepository;
import com.loofi.asset.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private final RealiseGainRepository realiseGainRepository;
    private final TransactionRepository transactionRepository;
    private final CurrentPriceRepository currentPriceRepository;
    private final AccountRepository accountRepository;

    public BigDecimal getRealisedGain(Long accountId, Long assetId) {
        return this.realiseGainRepository.calculateTotalRealizedGain(accountId, assetId);
    }

    public BigDecimal getUnRealisedGain(Long accountId, Long assetId) {
        BigDecimal totalCostBasis = transactionRepository.calculateTotalCostBasis(accountId, assetId);
        CurrentPrice currentPrice = currentPriceRepository.findByAssetId(assetId);
        if (currentPrice == null) {
            return BigDecimal.ZERO;
        }
        Account account = this.accountRepository.findByIdAndAssetId(accountId, assetId);
        if (account == null) {
            return BigDecimal.ZERO;
        }
        return (currentPrice.getSpotPrice().multiply(account.getBalance())).subtract(totalCostBasis);
    }
}
