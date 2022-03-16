package com.f1.f1results.integration;

import com.f1.f1results.security.jwt.UsernameAndPasswordAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RetrieveJwtToken {


    public static String retrieveJwtToken(MockMvc mockMvc, ObjectMapper objectMapper) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UsernameAndPasswordAuthenticationRequest("admin", "admin"))))
                .andReturn();
        return mvcResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    }


}
