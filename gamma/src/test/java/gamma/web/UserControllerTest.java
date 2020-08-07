package gamma.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")).
                andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void loginError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login-error")).
                andExpect(status().isFound())
                .andExpect(view().name("redirect:/login-error"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void onLoginError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")).
                andExpect(status().isForbidden());
    }
}