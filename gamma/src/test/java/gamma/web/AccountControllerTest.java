package gamma.web;

import gamma.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void accounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/list")).
                andExpect(status().isOk())
                .andExpect(view().name("accounts"))
                .andExpect(model().attributeExists("listOfAccounts","user"));
    }



    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void newAccountsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/new")).
                andExpect(status().isOk())
                .andExpect(view().name("new-account"))
                .andExpect(model().attributeExists("accountsAddBindingModel"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER"})
    void newAccountsPostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/new")).
                andExpect(status().isForbidden());
    }
}