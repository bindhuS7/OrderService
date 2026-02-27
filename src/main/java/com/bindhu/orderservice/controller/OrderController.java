package com.bindhu.orderservice.controller;


import com.bindhu.orderservice.dto.OrderRequest;
import com.bindhu.orderservice.dto.OrderResponse;
import com.bindhu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody OrderRequest request){
        log.info("entering createOrder of orderController");
        return orderService.createOrder(request);
    }

}
