package com.gy.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class LoginVo {

    private String username;
    private String password;

    @NotEmpty(message = "验证码不能为空")
    private String code;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号格式不正确")
    private String phone;

}
