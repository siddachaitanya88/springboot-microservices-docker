package com.microservices.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderLineItemsdto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import com.microservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	@Autowired
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	private Tracer tracer;

	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItemslist = orderRequest.getOrderLineItemsdtoList().stream().map(this::mapToDto)
				.toList();
		order.setOrderLineItemsList(orderLineItemslist);
		List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

		Span inventoryServiceLookUp = tracer.nextSpan().name("Inventory-Service-LookUp");
		try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookUp.start())) {
			// call other service to search for the availability of a product before you
			// save it in order
			InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
					.uri("http://inventory-service/api/inventory",
							uriBuilder -> uriBuilder.queryParam("SkuCodes", skuCodes).build())
					.retrieve().bodyToMono(InventoryResponse[].class).block();
			boolean allProductsInStcok = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
			if (allProductsInStcok) {
				orderRepository.save(order);
				return "order placed successfully";

			} else {
				throw new IllegalArgumentException("product is not in stock !! please try again later");

			}

		} finally {
			inventoryServiceLookUp.end();
		}

	}

	private OrderLineItems mapToDto(OrderLineItemsdto orderRequest) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderRequest.getPrice());
		orderLineItems.setOrderNumber(orderRequest.getOrderNumber());
		orderLineItems.setQuantity(orderRequest.getQuantity());
		orderLineItems.setSkuCode(orderRequest.getSkuCode());
		return orderLineItems;
	}

	public void getOrder() {

		orderRepository.findAll();
	}

}
