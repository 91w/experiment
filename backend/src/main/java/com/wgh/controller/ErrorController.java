package com.wgh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wsk1103 on 2017/5/23.
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error2")
    public String error() {
        return "error";
    }

}
