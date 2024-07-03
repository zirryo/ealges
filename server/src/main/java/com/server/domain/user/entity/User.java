package com.server.domain.user.entity;

import com.server.domain.model.Address;
import com.server.domain.model.Email;
import com.server.domain.model.Password;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "USERS")
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"userId"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;

    @Column(nullable = false, updatable = false)
    private String userName;

    @Column(nullable = false, updatable = false)
    private String loginId;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Column(nullable = false, updatable = false)
    private String phoneNum;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

}
