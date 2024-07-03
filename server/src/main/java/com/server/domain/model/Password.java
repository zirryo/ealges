package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "password", nullable = false)
    private String value;

    public Password(String value) {
        this.value = encodePassword(value);
    }

    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public void changePassword(final String oldPassword, final String newPassword) {
        if (isSame(oldPassword)) {
            value = encodePassword(newPassword);
        }
    }

    private boolean isSame(final String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, value);
    }

}
