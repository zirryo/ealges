package com.server.domain.user.service;

import com.server.domain.user.dao.UserRepository;

import com.server.domain.user.dto.UserDto;
import com.server.domain.user.entity.User;
import com.server.domain.user.exception.LoginIdDuplicationException;
import com.server.domain.user.exception.MemberNotFoundException;
import com.server.domain.user.exception.PhoneNumDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        final Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new MemberNotFoundException(id));
        return user.get();
    }

    @Transactional(readOnly = true)
    public boolean isExistedLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean isExistedPhoneNum(String phoneNUm) {
        return userRepository.existsByPhoneNum(phoneNUm);
    }

    public void modifyAddress(long id, UserDto.ModifyAddressReq dto) {
        final User user = findById(id);
        user.updateAddress(dto);
        userRepository.save(user);
    }

    public void signUpUser(UserDto.SignUpReq dto) {
        if (isExistedLoginId(dto.getLoginId())) {
            throw new LoginIdDuplicationException(dto.getLoginId());
        }
        if (isExistedPhoneNum(dto.getPhoneNum())) {
            throw new PhoneNumDuplicationException(dto.getPhoneNum());
        }

        dto.setPassword(encodePassword(dto.getPassword()));
        userRepository.save(dto.toEntity());
    }

    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<User> user = userRepository.findByLoginId(name);
        return user.orElseThrow(() -> new AuthenticationServiceException("Authentication exception"));
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
