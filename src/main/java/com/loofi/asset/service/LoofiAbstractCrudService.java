package com.loofi.asset.service;

import com.loofi.asset.entities.LoofiAbstractEntity;
import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import com.loofi.asset.repository.LoofiAbstractEntityRepository;
import com.loofi.asset.specification.LoofiAbstractCrudSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public abstract class LoofiAbstractCrudService<T extends LoofiAbstractEntity> {

    protected final LoofiAbstractEntityRepository<T> abstractEntityRepository;
    protected final LoofiAbstractCrudSpecification<T> specification;

    public LoofiAbstractCrudService(LoofiAbstractEntityRepository<T> abstractEntityRepository,
                                    LoofiAbstractCrudSpecification<T> specification) {
        this.abstractEntityRepository = abstractEntityRepository;
        this.specification = specification;
    }

    public Page<T> findAll(int page, int limit) {
        return this.abstractEntityRepository.findAll(PageRequest.of(page, limit));
    }

    public Page<T> findAll(int page, int limit, Sort.Direction direction, String... properties) {
        return this.abstractEntityRepository.findAll(PageRequest.of(page, limit, direction, properties));
    }

    public SearchResp<T> genericSearch(SearchReq searchReq) {
        SearchResp<T> finalResp = new SearchResp<>();
        Page<T> resp;
        if (searchReq.getSearchParams() == null || searchReq.getSearchParams().isEmpty()) {
            if (searchReq.getDirection() != null && searchReq.getProperties() != null) {
                Sort.Direction direction;
                if (SearchReq.Direction.ASC.name().equals(searchReq.getDirection().name())) {
                    direction = Sort.Direction.ASC;
                } else {
                    direction = Sort.Direction.DESC;
                }
                resp = findAll(searchReq.getPage(), searchReq.getLimit(), direction,
                        searchReq.getProperties());
            } else {
                resp = findAll(searchReq.getLimit(), searchReq.getPage());
            }
        } else {
            Specification<T> spec = searchReq.getSearchParams().stream()
                    .map(this.specification::getSpec)
                    .filter(Objects::nonNull)
                    .reduce(Specification::and).orElse(null);
            if (searchReq.getDirection() != null && searchReq.getProperties() != null) {
                Sort.Direction direction;
                if (SearchReq.Direction.ASC.name().equals(searchReq.getDirection().name())) {
                    direction = Sort.Direction.ASC;
                } else {
                    direction = Sort.Direction.DESC;
                }
                resp = this.abstractEntityRepository.findAll(Specification.where(spec),
                        PageRequest.of(searchReq.getPage(), searchReq.getLimit(), direction,
                                searchReq.getProperties()));
            } else {
                resp = this.abstractEntityRepository.findAll(Specification.where(spec),
                        PageRequest.of(searchReq.getPage(), searchReq.getLimit()));
            }
        }
        finalResp.setCount(resp.getTotalElements());
        finalResp.setData(resp.getContent());
        return finalResp;
    }
}
