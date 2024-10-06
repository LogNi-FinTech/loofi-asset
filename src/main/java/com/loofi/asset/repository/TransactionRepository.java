package com.loofi.asset.repository;

import com.loofi.asset.entities.Transaction;
import com.loofi.asset.models.enums.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends LoofiAbstractEntityRepository<Transaction> {

    Transaction findFirstByTransactionTypeAndAssetIdAndAccountIdOrderByIdDesc(TransactionType transactionType, Long assetId, Long accountId);

    List<Transaction> findAllByAssetIdAndAccountId(Long assetId, Long accountId, Pageable pageable);

    @Query("SELECT COALESCE(SUM(t.quantity * t.unitPrice), 0) " +
            "FROM Transaction t " +
            "WHERE t.account.id = :accountId " +
            "AND t.asset.id = :assetId " +
            "AND t.transactionType = 'BUY'")
    BigDecimal calculateTotalCostBasis(@Param("accountId") Long accountId,
                                       @Param("assetId") Long assetId);
}
