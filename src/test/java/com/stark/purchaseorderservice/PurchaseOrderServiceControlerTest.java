package com.stark.purchaseorderservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.stark.purchase_order.PurchaseOrderRequest;
import com.stark.purchase_order.PurchaseOrderResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PurchaseOrderServiceControlerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPurchaseOrderRequest() {
        PurchaseOrderRequest body = new PurchaseOrderRequest();
        body.setPartCode("A");
        body.setQuantity(1);

        HttpEntity<PurchaseOrderRequest> postRequest = new HttpEntity<>(body);
        ResponseEntity<PurchaseOrderResponse> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/v1/purchase-order", postRequest, PurchaseOrderResponse.class);

        assertThat(response.getStatusCodeValue() == 201);
        assertThat(response.getBody().getPurchaseOrderReceiverId() != null);
        assertThat("A".equals(response.getBody().getPartCode()));
        assertThat(response.getBody().getQuantity() == 1);
    }
}
