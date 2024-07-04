package com.server.domain.user.controller;

import com.server.domain.user.dto.UserDto;
import com.server.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto.SignUpReq dto) {
        userService.signUpUser(dto);
        String response = dto.getRealName() + "님의 회원가입이 완료되었습니다.";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable Long id ,@Valid @RequestBody UserDto.ModifyAddressReq dto) {
        userService.modifyAddress(id, dto);
        String response = "주소가 변경되었습니다.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserInfo(@PathVariable Long id) {
        UserDto.Res response = new UserDto.Res(userService.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
