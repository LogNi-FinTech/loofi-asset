package com.loofi.asset.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SearchResp<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long count;
    private List<T> data;
}
