package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @jakarta.validation.constraints.Email
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    private String value;

    private Email(final String value) {
        this.value = value;
    }

    public static Email of(final String value) {
        return new Email(value);
    }

    public String getDomain() {
        final int index = value.indexOf("@");
        return index != -1 ? value.substring(index + 1) : null;
    }

    public String getId() {
        final int index = value.indexOf("@");
        return index != -1 ? value.substring(0, index) : null;
    }
}
