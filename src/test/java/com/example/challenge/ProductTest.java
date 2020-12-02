package com.example.challenge;

import com.example.challenge.controller.ProductController;
import com.example.challenge.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    public void save() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/products/save")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Teste\",\"value\":5.90,\"type\":\"S\",\"status\":\"A\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
    }

    @Test
    public void update() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/products/update/c3b92993-5f85-41c0-986c-09307c3614c6")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Computer\",\"value\":5.90,\"type\":\"S\",\"status\":\"A\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void delete() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/products/delete/95687866-873f-4d0e-9e24-269bc7336764");

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void findAll() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/products/findAll")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
