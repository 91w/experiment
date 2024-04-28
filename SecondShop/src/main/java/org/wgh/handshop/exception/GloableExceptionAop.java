package org.wgh.handshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author hlt
 * @Date 2019/11/27 14:49
 */

@ControllerAdvice
public class GloableExceptionAop {

    /**
     * runtime异常
     * */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String runtimeException(){
        return "出现runtime异常了，这里在捕获全局异常，相当于手写AOP捕获异常。";
    }
}
