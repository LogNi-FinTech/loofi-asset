package com.loofi.asset.controllers;

import com.loofi.asset.models.AssetDTO;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import com.loofi.asset.router.LoofiAbstractCrudRouter;
import com.loofi.asset.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/asset")
@Slf4j
public class AssetController implements LoofiAbstractCrudRouter<AssetDTO, AssetDTO> {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @Override
    public AssetDTO find(Long id) {
        return this.assetService.findById(id);
    }

    @Override
    public AssetDTO save(AssetDTO req) {
        return this.assetService.save(req);
    }

    @Override
    public AssetDTO update(AssetDTO req) {
        return this.assetService.update(req);
    }

    @Override
    public void delete(Long id) {
        this.assetService.delete(id);
    }

    @Override
    public SearchResp<AssetDTO> search(SearchReq searchReq) {
        return this.assetService.search(searchReq);
    }
}
