package com.loofi.asset.service;

import com.loofi.asset.entities.Asset;
import com.loofi.asset.models.AssetDTO;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import com.loofi.asset.repository.AssetRepository;
import com.loofi.asset.specification.LoofiAbstractCrudSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class AssetServiceImpl extends LoofiAbstractCrudService<Asset> implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository,
                            LoofiAbstractCrudSpecification<Asset> specification) {
        super(assetRepository, specification);
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetDTO findById(Long id) {
        Asset asset = this.assetRepository.findById(id).orElseThrow();
        AssetDTO assetDTO = new AssetDTO();
        BeanUtils.copyProperties(asset, assetDTO);
        return assetDTO;
    }

    @Override
    public AssetDTO save(AssetDTO assetDTO) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetDTO, asset);
        this.assetRepository.save(asset);
        BeanUtils.copyProperties(asset, assetDTO);
        return assetDTO;
    }

    @Override
    public AssetDTO update(AssetDTO assetDTO) {
        return save(assetDTO);
    }

    @Override
    public void delete(Long id) {
        this.assetRepository.deleteById(id);
    }

    @Override
    public SearchResp<AssetDTO> search(SearchReq searchReq) {
        SearchResp<Asset> searchResp = genericSearch(searchReq);
        SearchResp<AssetDTO> resp = new SearchResp<>();
        resp.setCount(searchResp.getCount());
        resp.setData(searchResp.getData().stream().map(asset -> {
            AssetDTO assetDTO = new AssetDTO();
            BeanUtils.copyProperties(asset, assetDTO);
            return assetDTO;
        }).collect(Collectors.toList()));
        return resp;
    }
}
