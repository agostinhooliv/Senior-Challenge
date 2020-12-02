package com.example.challenge.controller;

import com.example.challenge.model.Order;
import com.example.challenge.model.OrderItem;
import com.example.challenge.model.Product;
import com.example.challenge.repository.OrderRepository;
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
        ObjectMapper mapper = new ObjectMapper();
        Order order = new Order();

        if (uuid != null && !uuid.equals("")) {
            Optional<Order> order1 = orderService.findById(uuid);

            if (!order1.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            order.setIdOder(uuid);
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
                            balance += (productData.get().getValue() * jsonNode.get(1).asInt());
                            //Applying percentual of discount, just if is a product
                            if (productData.get().getType() == 'P') {
                                balance -= (balance * discount);
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
        order.setDiscount(discount);
        order.setBalance(balance);
        order.setStatus('O');

        OrderItem orderItem = new OrderItem(order, products);

        orderService.saveOrder(order);
        orderService.saveOrderItem(orderItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<?>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
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
