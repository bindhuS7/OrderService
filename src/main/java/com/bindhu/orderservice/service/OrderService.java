package com.bindhu.orderservice.service;

import com.bindhu.orderservice.dto.OrderRequest;
import com.bindhu.orderservice.dto.OrderResponse;
import com.bindhu.orderservice.entity.Order;
import com.bindhu.orderservice.fiegnClient.SagaClient;
import com.bindhu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private  final OrderRepository orderRepository;
    private final SagaClient sagaClient;

    public OrderResponse createOrder(OrderRequest request) {
        log.info("entering  createOrder of OrderService");
        Order order=Order.builder()
                .productCode(request.getProductCode())
                .amount(request.getAmount())
                .status("PENDING")
                .quantity(request.getQuantity())
        .build();
        orderRepository.save(order);
        Boolean flag;

        try {
            flag=sagaClient.startSaga(request);
        } catch (Exception e) {
            log.info("saga feign client error");
            throw new RuntimeException(e);

        }


if(flag){
    order.setStatus("SUCCESS");
    orderRepository.save(order);
}else {
    order.setStatus("FAILED");
    orderRepository.save(order);
}


      return OrderResponse.builder()
               .orderId(order.getId())
               .status(order.getStatus())
               .message("order creation is"+order.getStatus().toLowerCase())
        .build();


    }
}
