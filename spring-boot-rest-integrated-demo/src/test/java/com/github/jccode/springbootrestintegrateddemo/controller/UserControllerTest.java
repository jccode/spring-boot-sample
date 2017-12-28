package com.github.jccode.springbootrestintegrateddemo.controller;

import com.github.jccode.springbootrestintegrateddemo.form.UserForm;
import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootrestintegrateddemo.service.UserService;
import org.assertj.core.util.Lists;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User testUser;

    private static final String TEST_USER_NAME = "test_user";
    private static final String TEST_NOT_EXIST_USER = "empty_user";

    @Before
    public void setup() {
        testUser = new User();
        testUser.setName(TEST_USER_NAME);
        testUser.setAge(18);
        given(userService.findByName(TEST_USER_NAME)).willReturn(Lists.newArrayList(testUser));
        given(userService.findByName(TEST_NOT_EXIST_USER)).willReturn(Lists.newArrayList());
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
                .andExpect(jsonPath("error", is(true)));
    }

    @Test
    public void registerFailed() throws Exception {
        mvc.perform(post("/user/register"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(true)))
                .andExpect(jsonPath("payload", iterableWithSize(3)));
    }

    @Test
    public void registerSuccess() throws Exception {
        String name = "testname";
        UserForm userForm = new UserForm();
        userForm.setName(name);
        userForm.setAge(20);
        userForm.setPassword("111111");
        mvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("password", "111111")
                .param("age", "20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(false)))
                .andExpect(jsonPath("payload.name", is(name)))
                .andExpect(jsonPath("payload.age", is(20)));
    }

}
