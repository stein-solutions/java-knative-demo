package solutions.stein.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import solutions.stein.demo.entity.Category;
import solutions.stein.demo.entity.Product;
import solutions.stein.demo.repository.CategoryRepository;
import solutions.stein.demo.repository.ProductRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product = productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + product.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        String productJson = "{\"name\":\"New Product\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setName("Old Product");
        product = productRepository.save(product);

        String updatedProductJson = "{\"name\":\"Updated Product\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Product"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setName("Product to be deleted");
        product = productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + product.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCategoriesToProduct() throws Exception {
        // Create a category
        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        // Create a product with the category
        String productJson = "{ \"name\": \"Product with Category\", \"categories\": [{ \"id\": " + category.getId() + " }] }";

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Product with Category"))
                .andExpect(jsonPath("$.categories[0].id").value(category.getId()));
    }

}