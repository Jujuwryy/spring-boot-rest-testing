package com.george.restapitest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductController productController;

    Product RECORD_1 = new Product(1L, "Smartphone", "Latest smartphone with high-end features", 999);
    Product RECORD_2 = new Product(2L, "Laptop", "Powerful laptop for professionals", 1500);
    Product RECORD_3 = new Product(3L, "Headphones", "Noise-cancelling wireless headphones", 200);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getAllProductRecords() throws Exception {
        List<Product> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(productRepo.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", is("Headphones")));
    }

    @Test
    public void getProductById() throws Exception {
        Mockito.when(productRepo.findById(RECORD_1.getProductId())).thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Smartphone")));
    }

    @Test
    public void deleteProductById() throws Exception {

        Mockito.when(productRepo.findById(RECORD_2.getProductId())).thenReturn(java.util.Optional.of(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders.delete("/product/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createProductRecord() throws Exception {
        // Arrange
        Product savedProduct = new Product(4L, "Tablet", "Portable tablet with long battery life", 600);

        // Mock the behavior of the repository's save method
        Mockito.when(productRepo.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(savedProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Tablet")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("Portable tablet with long battery life")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(600)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(4)));
    }

    @Test
    public void updateProduct() throws Exception {
        // Arrange
        Product existingProduct = new Product(1L, "Smartphone", "Latest smartphone with high-end features", 999);
        Product updatedProduct = new Product(1L, "Updated Smartphone", "Updated smartphone with even more features", 1100);

        // Mock the repository behavior
        Mockito.when(productRepo.findById(existingProduct.getProductId())).thenReturn(java.util.Optional.of(existingProduct));
        Mockito.when(productRepo.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/product/1")  // Added ID to path for update
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Updated Smartphone")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("Updated smartphone with even more features")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(1100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(1)));
    }
}
