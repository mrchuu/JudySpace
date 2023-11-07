package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public ResponseEntity<ResponseMessage> handleInputValidationException(BindException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rm);
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleCommonException(BadCredentialsException e) {
        ResponseMessage rm = new ResponseMessage("Không tìm thấy", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(rm);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ResponseMessage> handleCommonException(Exception e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getMessage());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).body(rm);
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseMessage> handleCommonException(IllegalAccessException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(rm);
    }

}
