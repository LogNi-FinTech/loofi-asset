package com.loofi.asset.repository;

import com.loofi.asset.entities.Transaction;
import com.loofi.asset.models.enums.TransactionType;

public interface TransactionRepository extends LoofiAbstractEntityRepository<Transaction> {

    Transaction findFirstByTransactionTypeAndAssetIdAndAccountIdOrderByIdDesc(TransactionType transactionType, Long assetId, Long accountId);
}
