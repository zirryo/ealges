package com.server.domain.user.dto;

import com.server.domain.model.Address;
import com.server.domain.model.Email;
import com.server.domain.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {

        @NotEmpty
        private String realName;

        @NotEmpty
        private String loginId;

        @NotEmpty
        @Setter
        private String password;

        @Valid
        private Email email;

        @NotEmpty
        private String phoneNum;

        @Valid
        private Address address;

        @Builder
        public SignUpReq(String realName, String loginId, String password, Email email, String phoneNum, Address address) {
            this.realName = realName;
            this.loginId = loginId;
            this.password = password;
            this.email = email;
            this.phoneNum = phoneNum;
            this.address = address;
        }

        public User toEntity() {
            return User.builder()
                    .realName(this.realName)
                    .loginId(this.loginId)
                    .password(this.password)
                    .email(this.email)
                    .phoneNum(this.phoneNum)
                    .address(this.address)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ModifyAddressReq {

        private Address address;

        @Builder
        public ModifyAddressReq(final Address address) {
            this.address = address;
        }

    }

    @Getter
    public static class Res {

        private final String realName;
        private final String loginId;
        private final Email email;
        private final String phoneNum;
        private final Address address;

        public Res(User user) {
            this.realName = user.getRealName();
            this.loginId = user.getLoginId();
            this.email = user.getEmail();
            this.phoneNum = user.getPhoneNum();
            this.address = user.getAddress();
        }

    }

}
