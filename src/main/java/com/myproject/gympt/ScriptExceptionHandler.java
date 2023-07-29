package com.myproject.gympt;

import com.myproject.gympt.user.controller.UserController;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {UserController.class})
public class ScriptExceptionHandler {


    @ExceptionHandler(value = {NotReadablePropertyException.class})
    private String offScript(){
        return "wrong";
    }
}
