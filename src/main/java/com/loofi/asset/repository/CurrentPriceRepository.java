package com.loofi.asset.repository;

import com.loofi.asset.entities.CurrentPrice;

public interface CurrentPriceRepository extends LoofiAbstractEntityRepository<CurrentPrice> {

    CurrentPrice findByAssetId(Long assetId);
}
