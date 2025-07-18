package com.dailycodework.buynowdotcom.repository;

import com.dailycodework.buynowdotcom.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByProductId(Long productId);
}