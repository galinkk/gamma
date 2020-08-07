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
class AdminActionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void registerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register")).
                andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userAddBindingModel","roles"));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ADMIN"})
    void registerPostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")).
                andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void listOfUsersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list-users")).
                andExpect(status().isOk())
                .andExpect(view().name("list-user"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void changeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/change")).
                andExpect(status().isOk())
                .andExpect(view().name("change-user"))
                .andExpect(model().attributeExists("userAddBindingModel","roles"));
    }

}