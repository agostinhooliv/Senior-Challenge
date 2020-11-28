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
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Map<String, Object>> getAllOrders(@RequestParam(defaultValue = "0") int page,
//                                                            @RequestParam(defaultValue = "5") int size) {
//        List<Order> listOrders = new ArrayList<>();
//
//        Pageable paging = PageRequest.of(page, size);
//        Page<Order> productPage = orderService.findAll(paging);
//        listOrders = productPage.getContent();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("Orders", listOrders);
//        response.put("currentPage", productPage.getNumber());
//        response.put("totalItems", productPage.getTotalElements());
//        response.put("totalPages", productPage.getTotalPages());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Order> create(@Valid @RequestBody ObjectNode objectNode) {
//
//        List<Product> products = new ArrayList<>();
//        double balance = 0.0d;
//        float discount = (Float.parseFloat(objectNode.get("discount").asText()) / 100);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            ArrayNode arrayNode = (ArrayNode) mapper.readTree(String.valueOf(objectNode)).get("products");
//            if (arrayNode.isArray()) {
//                for (JsonNode jsonNode : arrayNode) {
//                    Optional<Product> productData = productService.findByName(String.valueOf(jsonNode.get(0).asText()));
//                    if (productData.isPresent()) {
//                        Product product = productData.get();
//                        //Isn't allowed to add inactives products
//                        if (product.getStatus() == 'A') {
//                            products.add(product);
//                            balance += (productData.get().getValue() * jsonNode.get(1).asInt());
//                            //Applying percentual of discount, just if is a product
//                            if (productData.get().getType() == 'P') {
//                                balance -= (balance * discount);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Order order = new Order(objectNode.get("description").asText(), new Date(), discount, balance, 'O');
//        OrderItem orderItem = new OrderItem(order, products);
//
//        orderService.saveOrder(order);
//        orderService.saveOrderItem(orderItem);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<HttpStatus> deleteOrder(@RequestParam(value = "uuid", required = true) UUID idOrder) {
//
//        Optional<Order> orderData = orderService.findById(idOrder);
//
//        if (orderData.isPresent()) {
//
//            try {
//                OrderItem orderItem = orderService.findByOrder(orderData.get());
//                orderService.deleteOrderItem(orderItem);
//                orderService.deleteOrder(orderData.get());
//                return new ResponseEntity<>(HttpStatus.OK);
//            } catch (Exception ex) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<HttpStatus> updateOrder(@RequestParam(value = "uuid", required = true) UUID idOrder, @RequestBody ObjectNode objectNode) {
//
//        List<Product> products = new ArrayList<>();
//        double balance = 0.0d;
//        float discount = (Float.parseFloat(objectNode.get("discount").asText()) / 100);
//        ObjectMapper mapper = new ObjectMapper();
//
//        Optional<Order> orderData = orderService.findById(idOrder);
//
//        if (orderData.isPresent()) {
//
//            try {
//
//                ArrayNode arrayNode = (ArrayNode) mapper.readTree(String.valueOf(objectNode)).get("products");
//                if (arrayNode.isArray()) {
//                    for (JsonNode jsonNode : arrayNode) {
//                        Optional<Product> productData = productService.findByName(String.valueOf(jsonNode.get(0).asText()));
//                        if (productData.isPresent()) {
//                            products.add(productData.get());
//                            balance += (productData.get().getValue() * jsonNode.get(1).asInt());
//                            //Applying percentual of discount, just if is a product and if order status is equals to 'O'
//                            if (productData.get().getType() == 'P' && orderData.get().getStatus() == 'O') {
//                                balance -= (balance * discount);
//                            }
//                        }
//                    }
//                }
//
//                Order order = new Order(objectNode.get("description").asText(), new Date(), discount, balance, objectNode.get("status").asText().charAt(0));
//                order.setIdOder(idOrder);
//
//                OrderItem orderItem = orderService.findByOrder(orderData.get());
//                orderItem.setOrder(order);
//                orderItem.setProducts(products);
//
//                orderService.saveOrder(order);
//                orderService.saveOrderItem(orderItem);
//                return new ResponseEntity<>(HttpStatus.OK);
//
//            } catch (Exception ex) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/teste")
    public ResponseEntity<HttpStatus> teste() {
        System.out.println("Teste");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
