package com.server.domain.user.service;

import com.server.domain.user.dao.UserRepository;
import com.server.domain.user.entity.User;
import com.server.domain.user.exception.LoginIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new LoginIdNotFoundException(loginId));
    }

}
