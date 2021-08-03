package com.servbyte.services.selfserviceapp.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.servbyte.services.selfserviceapp.model.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}