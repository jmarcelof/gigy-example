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
public class ResourceAuthTest {

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
    public void onlyAdminAndSignupCanCreateUser() throws Exception {
        mockMvc.perform(asAdmin(post("/user"))
                .content("{\"username\":\"username\",\"password\":\"password\"}"))
                .andExpect(status().isCreated());
        mockMvc.perform(asSignUp(post("/user"))
                .content("{\"username\":\"username2\",\"password\":\"password\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(asMortal(post("/user"))
                .content("{\"username\":\"username3\",\"password\":\"password\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void onlyAdminAndMortalsCanGetInfo() throws Exception {
        mockMvc.perform(asAdmin(get("/user")))
                .andExpect(status().isOk());
        mockMvc.perform(asMortal(get("/user")))
                .andExpect(status().isOk());

        mockMvc.perform(asSignUp(get("/user")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void onlyAdminCanListAllUsers() throws Exception {
        mockMvc.perform(asAdmin(get("/user/all")))
                .andExpect(status().isOk());

        mockMvc.perform(asSignUp(get("/user/all")))
                .andExpect(status().isForbidden());
        mockMvc.perform(asMortal(get("/user/all")))
                .andExpect(status().isForbidden());
    }

    private MockHttpServletRequestBuilder asAdmin(MockHttpServletRequestBuilder req) throws Exception {
        return req
                .header("Authorization", "Bearer " + TokenHelper.obtainAccessToken(mockMvc, "admin@example.com", "password"))
                .contentType("application/json;charset=UTF-8");
    }

    private MockHttpServletRequestBuilder asSignUp(MockHttpServletRequestBuilder req) throws Exception {
        return req
                .header("Authorization", "Bearer " + TokenHelper.obtainAccessToken(mockMvc, "signup", "password"))
                .contentType("application/json;charset=UTF-8");
    }

    private MockHttpServletRequestBuilder asMortal(MockHttpServletRequestBuilder req) throws Exception {
        return req
                .header("Authorization", "Bearer " + TokenHelper.obtainAccessToken(mockMvc, "peter@example.com", "password"))
                .contentType("application/json;charset=UTF-8");
    }
}
