package com.github.jccode.springbootrestdemo;

import com.github.jccode.springbootrestdemo.model.User;
import com.github.jccode.springbootrestdemo.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String TEST_USER_NAME = "Tom";
    private static final String TEST_NOT_EXIST_USER = "EmptyUser";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User tom;

    @Before
    public void setup() {
        tom = new User(TEST_USER_NAME, "tompass", 20);
        given(userService.find(TEST_USER_NAME)).willReturn(tom);
        given(userService.find(TEST_NOT_EXIST_USER)).willReturn(null);
    }

    @Test
    public void findAnExistUser() throws Exception {
        mvc.perform(get("/user/"+TEST_USER_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(false)))
                .andExpect(jsonPath("payload.name", is(TEST_USER_NAME)));
    }

    @Test
    public void findAnNotExistUser() throws Exception {
        mvc.perform(get("/user/"+TEST_NOT_EXIST_USER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(true)))
                .andDo(MockMvcResultHandlers.print());
    }
}
