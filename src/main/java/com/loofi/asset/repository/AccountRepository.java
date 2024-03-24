package com.loofi.asset.repository;

import com.loofi.asset.entities.Account;

public interface AccountRepository extends LoofiAbstractEntityRepository<Account> {

    Account findByUserIdAndAssetId(Long userId, Long assetId);
}
