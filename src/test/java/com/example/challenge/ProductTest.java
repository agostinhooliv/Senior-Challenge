package com.example.challenge;

import com.example.challenge.controller.ProductController;
import com.example.challenge.model.Product;
import com.example.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

@WebMvcTest(ProductController.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    public void createOrUpdate() throws Exception {

        UUID idProduct = UUID.fromString("4194b3e2-a4b0-4253-8b81-5fd8a79");

        Product product = new Product();
        product.setIdProduct(idProduct);
        product.setName("Computer2");
        product.setPrice(202.9);
        product.setStatus('A');
        product.setType('P');

        Mockito.when(productService.findById(idProduct)).thenReturn(Optional.of(product));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/products/createOrUpdate")
                //Just for update
                .param("uuid", idProduct.toString())
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Teste\",\"value\":5.90,\"type\":\"S\",\"status\":\"A\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void delete() throws Exception {

        UUID idProduct = UUID.fromString("fc8dbd60-078f-494d-b979-1b7c485c8abc");

        Product product = new Product();
        product.setIdProduct(idProduct);
        product.setType('P');
        product.setName("Car");
        product.setPrice(20000.0);
        product.setStatus('A');

        Mockito.when(productService.findById(idProduct)).thenReturn(Optional.of(product));

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/products/delete")
                .param("uuid", idProduct.toString());

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
