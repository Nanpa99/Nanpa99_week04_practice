package com.example.base_ij.jwt;

import com.example.base_ij.global.BaseResDto;
import com.example.base_ij.global.GlobalResDto;
import com.example.base_ij.exception.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j

public class AccessDeniedHandlerException implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.error("Forbidden : 접근 권한이 없습니다.");

        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        try (OutputStream os = response.getOutputStream()){

            BaseResDto baseResDto = new BaseResDto();
            baseResDto.setResultCode(ResultCode.ACCESS_NO_AUTH.getResultCode());
            baseResDto.setResultMessage(ResultCode.ACCESS_NO_AUTH.getResultMessage());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, baseResDto);
            os.flush();
        }

    }
}
