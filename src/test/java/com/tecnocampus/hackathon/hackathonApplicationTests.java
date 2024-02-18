package com.tecnocampus.hackathon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class hackathonApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String getAccessToken(String rol) throws Exception {
        System.out.println("getAccessToken");
        String user = "{\"username\":\"" + rol + "\",\"password\":\"password123\"}";

        return objectMapper.readTree(mockMvc.perform(post("http://localhost:8080/api/auth")
                .contentType("application/json")
                .content(user))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists())
                .andReturn().getResponse().getContentAsString())
                .get("access_token").asText();
    }


//    --------- Category ---------

    @Test
    void test() throws Exception {
        // ...
    }

}