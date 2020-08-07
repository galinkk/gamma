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
class LedgerEntityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void ledgerEntity() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/tables")).
                    andExpect(status().isOk())
                    .andExpect(view().name("/tables"))
                    .andExpect(model().attributeExists("accountsName","contragentsName","ledgerEntityAddBindingModel","user"));
    }


    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void listLedgerEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list-ledger-entity")).
                  andExpect(status().isOk())
                  .andExpect(view().name("list-ledger-entity"))
                  .andExpect(model().attributeExists("ledger"));
    }
}