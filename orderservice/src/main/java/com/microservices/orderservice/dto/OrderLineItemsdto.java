package com.microservices.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsdto {
	private Long id;
	private String orderNumber;
	private String skuCode;
	private  BigDecimal price;
	private int quantity;

}
