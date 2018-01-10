package com.github.jccode.springcloud.integrateddemo.user.controller;


import com.github.jccode.springcloud.integrateddemo.user.model.User;
import com.github.jccode.springcloud.integrateddemo.user.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User testUser;

    private static final String TEST_UNAME = "uname";
    private static final String TEST_NOT_EXIST_NAME = "empty-user";

    @Before
    public void setup() {
        testUser = new User();
        testUser.setName(TEST_UNAME);
        testUser.setMobile("18100001111");
        testUser.setPassword("pass");
        testUser.setSalt("salt");

        given(userService.findByName(TEST_UNAME)).willReturn(Lists.newArrayList(testUser));
        given(userService.findByName(TEST_NOT_EXIST_NAME)).willReturn(Lists.emptyList());
    }

    @Test
    public void findUserSuccess() throws Exception {
        mvc.perform(get("/"+TEST_UNAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(false)))
                .andExpect(jsonPath("payload.name", is(TEST_UNAME)));
    }

    @Test
    public void findUserFailed() throws Exception {
        mvc.perform(get("/"+TEST_NOT_EXIST_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("error", is(true)));
    }
}
