package com.bank.cards.service.userservice;

import com.bank.cards.dto.UserRegisterDto;
import com.bank.cards.entity.User;
import com.bank.cards.mapper.UserMapper;
import com.bank.cards.repository.UserRepository;
import com.bank.cards.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceSaveTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    private UserRegisterDto userRegisterDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("user@example.com");
        userRegisterDto.setPassword("John Doe");
        user = UserMapper.toEntity(userRegisterDto);
    }

    @Test
    public void testSaveUserSuccess() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(userRegisterDto);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testSaveUserWithExistingEmail() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(userRegisterDto);

        assertNull(savedUser);

        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void testSaveUserWithException() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Database connection")).when(userRepository).save(user);

        Exception exception = assertThrows(Exception.class, () -> {
            userService.saveUser(userRegisterDto);
        });

        assertEquals("Database connection", exception.getMessage());

        verify(userRepository, times(1)).save(user);
    }
}
