package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(name = "email", nullable = false)
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="Please check the email form")
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
