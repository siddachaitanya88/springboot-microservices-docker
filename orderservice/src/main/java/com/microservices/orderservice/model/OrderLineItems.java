package com.microservices.orderservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="t_orderLineItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String orderNumber;
	private String skuCode;
	private  BigDecimal price;
	private int quantity;
	
}
