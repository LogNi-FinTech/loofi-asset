package com.loofi.asset.service;

import com.loofi.asset.models.AssetDTO;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;

public interface AssetService {

    AssetDTO findById(Long id);

    AssetDTO save(AssetDTO assetDTO);

    AssetDTO update(AssetDTO assetDTO);

    void delete(Long id);

    SearchResp<AssetDTO> search(SearchReq searchReq);
}
