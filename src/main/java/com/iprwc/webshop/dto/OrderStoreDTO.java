package com.iprwc.webshop.dto;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Order;
import com.iprwc.webshop.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderStoreDTO {

    @NotNull
    private User user;

    @NotNull
    private Car car;

    @NotNull
    private String delivery_address;

    public Order toOrder() {
        Order order = new Order();
        order.setUser(user);
        order.setDelivery_address(delivery_address);
        order.setCar(car);

        return order;
    }

}
