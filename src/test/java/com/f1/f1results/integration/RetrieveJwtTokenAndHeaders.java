package com.f1.f1results.integration;

import com.f1.f1results.security.jwt.UsernameAndPasswordAuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class RetrieveJwtTokenAndHeaders {

    private static MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        RetrieveJwtTokenAndHeaders.mockMvc = mockMvc;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        RetrieveJwtTokenAndHeaders.objectMapper = objectMapper;
    }

    public static Header CONTENT_TYPE_JSON_HEADER = new Header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    public static String retrieveJwtToken() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UsernameAndPasswordAuthenticationRequest("admin", "admin"))))
                .andReturn();
        return mvcResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    }

    public static Header authorizationHeader() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UsernameAndPasswordAuthenticationRequest("admin", "admin"))))
                .andReturn();
        String token = mvcResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        return new Header(HttpHeaders.AUTHORIZATION, token);
    }

    public static String toJSON(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }


}
