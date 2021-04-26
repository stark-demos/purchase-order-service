package com.stark.purchaseorderservice;

import java.util.UUID;

import com.stark.purchase_order.PurchaseOrderRequest;
import com.stark.purchase_order.PurchaseOrderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseOrderServiceController {
    private static Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceController.class);

    @PostMapping("/v1/purchase-order")
    public PurchaseOrderResponse createPurchaseOrderForPartCode(@RequestBody PurchaseOrderRequest request) {
        logger.debug("Received Request to create a purchase order {}", request);

        PurchaseOrderResponse response = new PurchaseOrderResponse();
        response.setPartCode(request.getPartCode());
        response.setQuantity(request.getQuantity());
        response.setPurchaseOrderReceiverId(UUID.randomUUID().toString());
        return response;
    }
}
