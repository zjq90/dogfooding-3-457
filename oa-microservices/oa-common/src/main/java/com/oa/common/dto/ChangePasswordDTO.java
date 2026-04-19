package com.oa.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
