package com.poc.test;

import com.poc.App;
import com.poc.util.TokenHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = App.class)
public class Oauth2Test {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void requestWithoutTokenIsUnauthorized() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void requestWithTokenIsOk() throws Exception {
        mockMvc.perform(authorized(get("/user")))
                .andExpect(status().isOk());
    }

    @Test
    public void requestWithInvalidTokenIsUnauthorized() throws Exception {
        mockMvc.perform(get("/user").header("Authorization", "Bearer " + "abcd"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void requestWithoutRightRoleIsForbidden() throws Exception {
        mockMvc.perform(authorized(post("/user")))
                .andExpect(status().isForbidden());
    }

    private MockHttpServletRequestBuilder authorized(MockHttpServletRequestBuilder req) throws Exception {
        return req.header("Authorization", "Bearer " + TokenHelper.obtainAccessToken(mockMvc, "peter@example.com", "password"));
    }
}
