package com.jatismobile.unittest.repositories.unittest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatismobile.unittest.models.unittest.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

}
