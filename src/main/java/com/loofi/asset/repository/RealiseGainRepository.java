package com.loofi.asset.repository;

import com.loofi.asset.entities.RealiseGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface RealiseGainRepository extends JpaRepository<RealiseGain, Long> {

    @Query("SELECT COALESCE(SUM(rg.realisedGain), 0) " +
            "FROM RealiseGain rg " +
            "WHERE rg.account.id = :accountId " +
            "AND rg.asset.id = :assetId")
    BigDecimal calculateTotalRealizedGain(@Param("accountId") Long accountId,
                                          @Param("assetId") Long assetId);
}
