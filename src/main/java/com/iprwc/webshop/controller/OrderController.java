package com.iprwc.webshop.controller;

import com.iprwc.webshop.EmailDetails;
import com.iprwc.webshop.Message;
import com.iprwc.webshop.dao.CarDAO;
import com.iprwc.webshop.dao.OrderDAO;
import com.iprwc.webshop.dto.OrderStoreDTO;
import com.iprwc.webshop.model.ApiResponse;
import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.model.Order;
import com.iprwc.webshop.model.User;
import com.iprwc.webshop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private MailService mailService;

    private final OrderDAO orderDAO;
    private final CarDAO carDAO;

    public OrderController(OrderDAO orderDAO, CarDAO carDAO) {
        this.orderDAO = orderDAO;
        this.carDAO = carDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity store(@Valid @NotNull @RequestBody OrderStoreDTO orderStoreDTO) {
        Order order = orderStoreDTO.toOrder();
        Order returnedOrder = orderDAO.store(order);

        if (returnedOrder != null) {
            Car car = order.getCar();
            User user = order.getUser();
            car.setSold(true);

            carDAO.update(car);

            EmailDetails details = new EmailDetails();
            details.setBody(
                    "Hello " + user.getName() + " Your purchase of " + car.getTitle() + " will be delivered to " + order.getDelivery_address() + " shortly. Our delivery staff will be ready to take your payment."
            );
            details.setRecipient(user.getEmail());
            details.setSubject(car.getTitle());

            mailService.sendEmail(details);
            return new ApiResponse(HttpStatus.CREATED, order).getResponse();
        }

        return new ApiResponse(HttpStatus.BAD_REQUEST, new Message("Something went wrong with creating the order")).getResponse();
    }
}
