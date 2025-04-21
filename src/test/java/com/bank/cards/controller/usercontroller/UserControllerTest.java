package com.bank.cards.controller.usercontroller;

import com.bank.cards.controller.UserController;
import com.bank.cards.dto.UserRegisterDto;
import com.bank.cards.entity.User;
import com.bank.cards.mapper.UserMapper;
import com.bank.cards.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UserRegisterDto userRegisterDto;

    private User user;

    @BeforeEach
    public void setUp() {
        userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("test@example.com");
        userRegisterDto.setPassword("password123");
        user = UserMapper.toEntity(userRegisterDto);
    }

    @Test
    public void registerUserTestSuccess() throws Exception {
        when(userService.saveUser(userRegisterDto)).thenReturn(user);

        mockMvc.perform(post("/registration")
                .contentType("application/json")
                .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$")
                        .value("Registration success with email test@example.com"));
    }

    @Test
    public void registerTwiceUserTest() throws Exception {
        when(userService.saveUser(userRegisterDto)).thenReturn(null);

        mockMvc.perform(post("/registration")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$")
                        .value("User with email " + user.getEmail() + " already exists"));
    }

    @Test
    public void registerUserTestNotSuccess() throws Exception {
        when(userService.saveUser(userRegisterDto)).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(post("/registration")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                        .andExpect(status().isInternalServerError())
                        .andExpect(jsonPath("$").value("Error"));
    }
}
