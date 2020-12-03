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

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                                               @RequestParam(defaultValue = "5", required = false) int size) {
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

    @GetMapping("/byName")
    public ResponseEntity<Product> getProductsByName(@RequestParam(name = "name") String name) {

        Optional<Product> productData = productService.findByName(name);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Product> createOrUpdate(@RequestParam(name = "uuid", required = false) UUID uuid, @Valid @RequestBody Product product) {

        if (uuid != null && !uuid.equals("")) {
            Optional<Product> product1 = productService.findById(uuid);

            if (!product1.isPresent())
                new ResponseEntity(HttpStatus.NOT_FOUND);

            product.setIdProduct(uuid);
        }

        try {
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "uuid") UUID uuid) {

        try {

            Optional<Product> product = productService.findById(uuid);

            if (!product.isPresent())
                return new ResponseEntity(HttpStatus.NOT_FOUND);


            productService.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Can't exclude this product, it's been linked to an order!", HttpStatus.FORBIDDEN);
        }
    }
}
