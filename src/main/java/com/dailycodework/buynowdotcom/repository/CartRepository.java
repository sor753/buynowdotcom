package com.dailycodework.buynowdotcom.repository;

import com.dailycodework.buynowdotcom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}
