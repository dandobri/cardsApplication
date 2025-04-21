package com.bank.cards.service;

import com.bank.cards.dto.UserRegisterDto;
import com.bank.cards.entity.User;
import com.bank.cards.mapper.UserMapper;
import com.bank.cards.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public User saveUser(UserRegisterDto userDto) {
        try {
            logger.info("Starting to save user: {}", userDto.getEmail());
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                logger.warn("User with this email exists: {}", userDto.getEmail());
                return null;
            }

            User savedUser = userRepository.save(UserMapper.toEntity(userDto));
            logger.info("User saved successfully with ID: {}", savedUser.getId());

            return savedUser;
        } catch (Exception e) {
            logger.error("Error occurred while saving user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public User getUserByEmail(String email) {
        try {
            logger.info("Starting to get user by email: {}", email);
            Optional<User> user = userRepository.findByEmail(email);
            return user.orElse(null);
        } catch (Exception e) {
            logger.error("Error occurred while getting user by email: {}", e.getMessage(), e);
            throw e;
        }
    }
}
