package com.loofi.asset.models;

import com.loofi.asset.models.enums.TransactionStatus;
import com.loofi.asset.models.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal fee;
    private BigDecimal totalPrice;
    private BigDecimal midPrice;
    private BigDecimal effectiveSpread;
    private BigDecimal rawSpread;
    private String refId;
    private TransactionStatus status;
    private TransactionType transactionType;
    private String info;
}
