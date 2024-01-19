package com.loofi.asset.specification;

import com.loofi.asset.entities.LoofiAbstractEntity;
import com.loofi.asset.models.SearchReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Slf4j
public class LoofiAbstractCrudSpecification<T extends LoofiAbstractEntity> {

    public Specification<T> getSpec(SearchReq.SearchParam searchParam) {
        if (searchParam.getValue() != null) {
            if (SearchReq.Operation.EQUAL.name().equals(searchParam.getOperation().name())) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root.get(searchParam.getKey()),
                        searchParam.getValue()[0]
                );
            } else if (SearchReq.Operation.LIKE.name().equals(searchParam.getOperation().name())) {
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                    root.get(searchParam.getKey()), (String) searchParam.getValue()[0]);
            } else if (SearchReq.Operation.DATE_BETWEEN.name()
                .equals(searchParam.getOperation().name())) {
                return this.dateBetween(searchParam.getKey(), (String) searchParam.getValue()[0],
                    (String) searchParam.getValue()[1], (String) searchParam.getValue()[2]);
            }
        }
        return null;
    }

    public Specification<T> dateBetween(String key, String startDate, String endDate,
        String pattern) {
        if (startDate != null && endDate != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(key),
                this.toDate(pattern, startDate),
                this.toDate(pattern, endDate)
            );
        }
        return null;
    }

    public Instant toDate(String pattern, String date) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
            return localDateTime.toInstant(ZoneOffset.UTC);
        } catch (Exception ex) {
            log.error("Date can not be parsed : {}", ex.getMessage(), ex);
        }
        return null;
    }
}
