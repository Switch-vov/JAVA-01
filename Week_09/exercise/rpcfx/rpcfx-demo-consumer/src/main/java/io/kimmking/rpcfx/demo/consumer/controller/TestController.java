package io.kimmking.rpcfx.demo.consumer.controller;

import io.kimmking.rpcfx.annotation.Reference;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author switch
 * @since 2021/5/9
 */
@Slf4j
@RestController
public class TestController {
    @Reference(url = "http://localhost:8080/")
    private UserService userService;

    @Reference(url = "http://localhost:8080/")
    private OrderService orderService;

    @RequestMapping("/test")
    public String  findTest() {
        User user = userService.findById(1);
        log.info("user:{}", user);

        Order order = orderService.findOrderById(1992129);
        log.info("order:{}", order);
        return "findTest";
    }
}
