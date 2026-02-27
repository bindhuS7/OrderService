package com.bindhu.orderservice.fiegnClient;

import com.bindhu.orderservice.dto.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sagaservice")
public interface SagaClient {
    @PostMapping("/saga/start")
    public Boolean startSaga(@RequestBody OrderRequest request) ;
    }

