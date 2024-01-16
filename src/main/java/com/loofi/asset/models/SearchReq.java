package com.loofi.asset.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SearchReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<SearchParam> searchParams;
    private Integer limit;
    private Integer page;
    @JsonProperty("sortedDirection")
    private Direction direction;
    @JsonProperty("sortedField")
    private String properties;

    public enum Operation {
        EQUAL,
        DATE_BETWEEN,
        LIKE
    }

    public enum Direction {
        ASC,
        DESC
    }

    public enum ObjectType {
        Integer,
        Long,
        String,
        Boolean,
        Date
    }

    @Getter
    @Setter
    public static class SearchParam implements Serializable {

        private String key;
        private Object[] value;
        private Operation operation;
    }
}
