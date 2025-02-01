package solutions.stein.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import solutions.stein.demo.entity.Category;
import solutions.stein.demo.repository.CategoryRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + category.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Category"));
    }

    @Test
    public void testCreateCategory() throws Exception {
        String categoryJson = "{\"name\":\"New Category\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Category"));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setName("Old Category");
        category = categoryRepository.save(category);

        String updatedCategoryJson = "{\"name\":\"Updated Category\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCategoryJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Category category = new Category();
        category.setName("Category to be deleted");
        category = categoryRepository.save(category);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + category.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductsByCategoryId() throws Exception {
        Category category = new Category();
        category.setName("Category with Products");
        category = categoryRepository.save(category);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + category.getId() + "/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}