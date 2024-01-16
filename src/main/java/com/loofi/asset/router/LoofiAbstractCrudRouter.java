package com.loofi.asset.router;

import com.loofi.asset.models.SearchReq;
import com.loofi.asset.models.SearchResp;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoofiAbstractCrudRouter<T, E> {

    @GetMapping("/{id}")
    T find(@PathVariable Long id);

    @PostMapping("")
    T save(@RequestBody E req);

    @PutMapping("")
    T update(@RequestBody E req);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @PostMapping("/search")
    SearchResp<T> search(@RequestBody SearchReq searchReq);
}
