package com.example.challenge.controller;

import com.example.challenge.model.Product;
import com.example.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllPageable")
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size) {
        List<Product> listProducts = new ArrayList<>();

        Pageable paging = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAll(paging);
        listProducts = productPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("Products", listProducts);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Product>> getAll2() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Product> getProductsByName(@PathVariable String name) {

        Optional<Product> productData = productService.findByName(name);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<Product> update(@PathVariable UUID uuid, @RequestBody Product product) {

        Optional<Product> product1 = productService.findById(uuid);

        if (!product1.isPresent())
            new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID uuid) {

        try {

            Optional<Product> product = productService.findById(uuid);

            if (!product.isPresent())
                new ResponseEntity(HttpStatus.NOT_FOUND);


            productService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Can't exclude this product, it's been linked to an order!", HttpStatus.FORBIDDEN);
        }
    }
}
