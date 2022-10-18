package com.sparta.testjwt.service;

import com.sparta.testjwt.domain.User;
import com.sparta.testjwt.dto.request.UserDto;
import com.sparta.testjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String addUser(UserDto dto){
            Optional<User> found = userRepository.findByUsername(dto.getUsername());
            if (found.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
            }
            String password = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getUsername(), password);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
