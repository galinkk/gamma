package gamma.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = {"ACCOUNTING","ADMIN", "ACCOUNTING_MANAGER"})
    void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).
                andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void homeAbsolute(@AuthenticationPrincipal Principal principal, ModelAndView model) throws Exception {

    }

    @Test
    void homePost() {
    }

    @Test
    void approve() {
    }

    @Test
    void testApprove() {
    }

    @Test
    void approvePost() {
    }
}