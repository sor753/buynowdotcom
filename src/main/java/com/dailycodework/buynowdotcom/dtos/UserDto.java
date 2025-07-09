package com.dailycodework.buynowdotcom.dtos;

import com.dailycodework.buynowdotcom.model.Cart;
import com.dailycodework.buynowdotcom.model.Order;
import com.dailycodework.buynowdotcom.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Cart cart;
    private List<Order> orders;
    private List<Role> roles;
}
