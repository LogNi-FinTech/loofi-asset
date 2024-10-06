package com.loofi.asset.repository;

import com.loofi.asset.entities.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

public interface AccountRepository extends LoofiAbstractEntityRepository<Account> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findByUserIdAndAssetId(Long userId, Long assetId);

    Account findByIdAndAssetId(Long id, Long assetId);
}
