package com.servbyte.services.selfserviceapp.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servbyte.services.selfserviceapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}