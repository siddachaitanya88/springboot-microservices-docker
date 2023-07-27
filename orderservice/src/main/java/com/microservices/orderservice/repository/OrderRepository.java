package com.microservices.orderservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
