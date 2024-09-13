package com.pragmatic.testing;

import com.pragmatic.controller.AccountController;
import com.pragmatic.model.Account;
import com.pragmatic.service.AccountServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.containsString;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class AccountControllerIntegrationTest { //чому тут забирати паблык потрібно?
    @Autowired
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  AccountServiceImpl accountServiceImpl;


    private Account accountBuilder() {
        return new Account().setId(1)
                .setCurrencyId(1)
                .setUserId(1)
                .setBalance(0.0);
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void helloWorldTest() throws Exception {
        assertThat(accountController).isNotNull();
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Hello Test")));
    }

    @Test
    public void getBrokenUrlTest() throws Exception {
        assertThat(accountController).isNotNull();
        this.mockMvc.perform(get("/getBadRequest"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void getAccountByIdTest() throws Exception {
        assertThat(accountController).isNotNull();
        //чи це не те саме, як мокіто вен.
        Account testAccount = accountBuilder();
        given(accountServiceImpl.getAccountById(testAccount.getId())).willReturn(Optional.of(testAccount));
        ResultActions response = mockMvc.perform(get("/getAccountById?id={id}", testAccount.getId()));

        response.andExpect(status().isOk())
                //.andDo(print())
                .andExpect(jsonPath("$.currencyId", is(testAccount.getCurrencyId())))
                .andExpect(jsonPath("$.userId", is(testAccount.getUserId())));

        when(accountServiceImpl.getAccountById(2)).thenReturn(Optional.ofNullable(null));
        //ПЕРЕПИТАТИ ЧИ ЕКСПЕШЕНИ ТЕСТИТИ ЯКРАЗ ТАК, ЯК ТУТ ОПИСАНО.
        ResultActions nullAccountResponse = mockMvc.perform(get("/getAccountById?id={id}", 2));
        nullAccountResponse.andExpect(status().isNotFound())
                .andDo(print());
    }


    @Test
    public void getForbidden() throws Exception {
        mockMvc.perform(get("/getForbidden"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getNotFound() throws Exception {
        mockMvc.perform(get("/getNotFound"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getPermanentRedirect() throws Exception {
        mockMvc.perform(get("/getPermanentRedirect"))
                .andExpect(status().isMovedPermanently());
    }

    @Test
    public void getRedirect() throws Exception {
        mockMvc.perform(get("/getRedirect"))
                .andExpect(status().isMovedTemporarily());
    }

}
