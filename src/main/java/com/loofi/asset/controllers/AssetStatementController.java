package com.loofi.asset.controllers;

import com.loofi.asset.models.TransactionDto;
import com.loofi.asset.service.AssetStatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/statement")
@Slf4j
@AllArgsConstructor
public class AssetStatementController {

    private final AssetStatementService assetStatementService;

    @GetMapping
    List<TransactionDto> getStatements(@RequestParam("assetId") Long assetId,
                                       @RequestParam("accountId") Long accountId,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("size") Integer size) {
        return this.assetStatementService.getStatements(assetId, accountId, page, size);
    }
}
