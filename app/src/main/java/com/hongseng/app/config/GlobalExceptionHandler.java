package com.hongseng.app.config;

import enums.ErrorCodeEnum;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;


/**
 * @program: fire_control
 * @description: 处理自定义的业务异常
 * @author: fbl
 * @create: 2021-01-15 16:21
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * token过期
     * @return
     */
    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public Result expiredJwtException(){
        return Result.failure(ErrorCodeEnum.SYS_ERR_TOKEN_EXPIRED);
    }


    /**
     * token错误
     * @return
     */
    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public Result signatureException(){
        return Result.failure(ErrorCodeEnum.SYS_ERR_TOKEN_SIGNATURE);
    }

}
