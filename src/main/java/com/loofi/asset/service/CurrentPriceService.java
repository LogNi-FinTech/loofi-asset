package com.loofi.asset.service;

import com.loofi.asset.entities.Asset;
import com.loofi.asset.entities.CurrentPrice;
import com.loofi.asset.models.AssetDTO;
import com.loofi.asset.models.PriceDto;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import com.loofi.asset.repository.AssetRepository;
import com.loofi.asset.repository.CurrentPriceRepository;
import com.loofi.asset.repository.LoofiAbstractEntityRepository;
import com.loofi.asset.specification.LoofiAbstractCrudSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class CurrentPriceService extends LoofiAbstractCrudService<CurrentPrice> {

    private final CurrentPriceRepository currentPriceRepository;
    private final AssetRepository assetRepository;

    public CurrentPriceService(CurrentPriceRepository currentPriceRepository,
                               AssetRepository assetRepository,
                               LoofiAbstractCrudSpecification<CurrentPrice> specification) {
        super(currentPriceRepository, specification);
        this.currentPriceRepository = currentPriceRepository;
        this.assetRepository = assetRepository;
    }

    public PriceDto findById(Long id) {
        CurrentPrice currentPrice = this.currentPriceRepository.findById(id).orElseThrow();
        PriceDto priceDto = new PriceDto();
        BeanUtils.copyProperties(currentPrice, priceDto);
        if (currentPrice.getAsset() != null) {
            priceDto.setAsset(new AssetDTO());
            BeanUtils.copyProperties(currentPrice.getAsset(), priceDto.getAsset());
        }
        return priceDto;
    }

    public PriceDto save(PriceDto priceDto) {
        CurrentPrice currentPrice = new CurrentPrice();
        BeanUtils.copyProperties(priceDto, currentPrice);
        Asset asset = this.assetRepository.findById(priceDto.getAssetId()).orElseThrow();
        currentPrice.setAsset(asset);
        this.currentPriceRepository.save(currentPrice);
        BeanUtils.copyProperties(currentPrice, priceDto);
        if (currentPrice.getAsset() != null) {
            priceDto.setAsset(new AssetDTO());
            BeanUtils.copyProperties(currentPrice.getAsset(), priceDto.getAsset());
        }
        return priceDto;
    }

    public PriceDto update(PriceDto priceDto) {
        return save(priceDto);
    }

    public void delete(Long id) {
        this.currentPriceRepository.deleteById(id);
    }

    public SearchResp<PriceDto> search(SearchReq searchReq) {
        SearchResp<CurrentPrice> searchResp = genericSearch(searchReq);
        SearchResp<PriceDto> resp = new SearchResp<>();
        resp.setCount(searchResp.getCount());
        resp.setData(searchResp.getData().stream().map(currentPrice -> {
            PriceDto priceDto = new PriceDto();
            BeanUtils.copyProperties(currentPrice, priceDto);
            if (currentPrice.getAsset() != null) {
                priceDto.setAsset(new AssetDTO());
                BeanUtils.copyProperties(currentPrice.getAsset(), priceDto.getAsset());
            }
            return priceDto;
        }).collect(Collectors.toList()));
        return resp;
    }
}
