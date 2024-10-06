package com.loofi.asset.controllers;

import com.loofi.asset.service.PortfolioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/portfolio")
@Slf4j
@AllArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/realised-gain")
    BigDecimal getRealisedGain(@RequestParam("assetId") Long assetId,
                               @RequestParam("accountId") Long accountId) {
        return this.portfolioService.getRealisedGain(accountId, assetId);
    }

    @GetMapping("/unrealised-gain")
    BigDecimal getUnRealisedGain(@RequestParam("assetId") Long assetId,
                               @RequestParam("accountId") Long accountId) {
        return this.portfolioService.getUnRealisedGain(accountId, assetId);
    }
}
