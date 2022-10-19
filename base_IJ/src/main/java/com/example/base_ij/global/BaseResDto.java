package com.example.base_ij.global;

import com.example.base_ij.exception.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResDto {
    private int resultCode = ResultCode.SUCCESS.getResultCode();
    private String resultMessage = ResultCode.SUCCESS.getResultMessage();
}
