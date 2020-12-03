package com.example.challenge.controller;

import com.example.challenge.model.Order;
import com.example.challenge.model.OrderItem;
import com.example.challenge.model.Product;
import com.example.challenge.service.OrderService;
import com.example.challenge.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<?> createOrUpdate(@RequestParam(name = "uuid", required = false) UUID uuid, @Valid @RequestBody ObjectNode objectNode) {

        List<Product> products = new ArrayList<>();
        double balance = 0.0d;
        float discount = (Float.parseFloat(objectNode.get("discount").asText()) / 100);
        char status = objectNode.get("status").toString().charAt(1);
        ObjectMapper mapper = new ObjectMapper();
        Order order = new Order();

        if (uuid != null && !uuid.equals("")) {
            Optional<Order> order1 = orderService.findById(uuid);

            if (!order1.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            order = order1.get();
        }

        try {
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(String.valueOf(objectNode)).get("products");
            if (arrayNode.isArray()) {
                for (JsonNode jsonNode : arrayNode) {
                    Optional<Product> productData = productService.findByName(String.valueOf(jsonNode.get(0).asText()));
                    if (productData.isPresent()) {
                        Product product = productData.get();
                        //Isn't allowed to add inactives products
                        if (product.getStatus() == 'A') {
                            products.add(product);
                            balance += (productData.get().getPrice() * jsonNode.get(1).asInt());
                            //Applying percentual of discount, just if is a product and order status is not 'C'
                            if (productData.get().getType() == 'P') {
                                if (order.getStatus() != 'C') {
                                    balance -= (balance * discount);
                                    order.setDiscount(discount);
                                } else{
                                    order.setDiscount(order.getDiscount());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        order.setDescription(objectNode.get("description").asText());
        order.setDate(new Date());
        order.setBalance(balance);

        if (status != 0) {
            order.setStatus(status);
        } else {
            order.setStatus('O');
        }

        OrderItem orderItem = new OrderItem(order, products);

        orderService.saveOrder(order);
        orderService.saveOrderItem(orderItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "5", required = false) int size) {

        List<Order> orderList = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Order> orderPage = orderService.findAll(paging);
        orderList = orderPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Orders", orderList);
        response.put("currentPage", orderPage.getNumber());
        response.put("totalItems", orderPage.getTotalElements());
        response.put("totalPages", orderPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "uuid") UUID uuid) {

        Optional<Order> order = orderService.findById(uuid);

        if (!order.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        orderService.deleteOrderItem(uuid);
        orderService.deleteOrder(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
