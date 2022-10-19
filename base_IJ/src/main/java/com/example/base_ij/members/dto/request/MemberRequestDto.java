package com.example.base_ij.members.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor

public class MemberRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    //@Size(min = 4, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]{4,12}", message = "닉네임을 영어(대문자 포함)와 숫자를 포함해서 4~12자리 이내로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    //@Size(min = 4)
    @Pattern(regexp = "[a-z0-9]{4,32}", message = "비밀번호를 소문자(대문자 미포함)와 숫자를 포함해서 4~32자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "[a-z1-9]{4,32}", message = "비밀번호를 소문자(대문자 미포함)와 숫자를 포함해서 4~32자리 이내로 입력해주세요.")
    private String passwordConfirm;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    public MemberRequestDto(String nickname, String password, String passwordConfirm, String email) {
        this.nickname = getNickname();
        this.password = getPassword();
        this.passwordConfirm = getPasswordConfirm();
        this.email = getEmail();
    }

    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }
}
