package com.loofi.asset.controllers;

import com.loofi.asset.models.PriceDto;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import com.loofi.asset.router.LoofiAbstractCrudRouter;
import com.loofi.asset.service.CurrentPriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/price")
@Slf4j
@AllArgsConstructor
public class AssetPriceController implements LoofiAbstractCrudRouter<PriceDto, PriceDto> {

    private final CurrentPriceService currentPriceService;

    @Override
    public PriceDto find(Long id) {
        return this.currentPriceService.findById(id);
    }

    @Override
    public PriceDto save(PriceDto req) {
        return this.currentPriceService.save(req);
    }

    @Override
    public PriceDto update(PriceDto req) {
        return this.currentPriceService.update(req);
    }

    @Override
    public void delete(Long id) {
        this.currentPriceService.delete(id);
    }

    @Override
    public SearchResp<PriceDto> search(SearchReq searchReq) {
        return this.currentPriceService.search(searchReq);
    }
}
