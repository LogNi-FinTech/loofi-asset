package com.loofi.asset.service;

import com.loofi.asset.entities.Transaction;
import com.loofi.asset.models.TransactionDto;
import com.loofi.asset.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AssetStatementService {

    private final TransactionRepository transactionRepository;

    public List<TransactionDto> getStatements(Long assetId, Long accountId, Integer page, Integer size) {
        List<Transaction> transactionList = this.transactionRepository.findAllByAssetIdAndAccountId(
                assetId,
                accountId,
                PageRequest.of(page, size)
        );
        return transactionList.parallelStream().map(transaction -> {
            TransactionDto transactionDto = new TransactionDto();
            BeanUtils.copyProperties(transaction, transactionDto);
            return transactionDto;
        }).collect(Collectors.toList());
    }
}
