package solutions.stein.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import solutions.stein.demo.controller.AppStartStatResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	    @Test
    void testAppStartStatsEndpoint() throws Exception {
        var res = mockMvc.perform(get("/appStartStats"))
                .andExpect(status().isOk()).andReturn();

        var tmp = res.getResponse().getContentAsString();
        AppStartStatResponse response = objectMapper.readValue(tmp, AppStartStatResponse.class);

        // Perform assertions on the parsed object
        assertNotNull(response);
        assertTrue(response.getStartTimestamp() > 0);
        assertTrue(response.getInitializedTimestamp() > 0);
        assertTrue(response.getRequestReceivedTimestamp() > 0);
    }


}
