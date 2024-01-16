package com.loofi.asset.repository;

import com.loofi.asset.entities.LoofiAbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LoofiAbstractEntityRepository<T extends LoofiAbstractEntity> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, Long> {
}
