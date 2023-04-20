package com.zsun.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:HelloController
 * Package:com.zsun.springboot.controller
 * Description:
 *
 * @author zy
 * @create 2023-04-1714:59
 * @Version 1.0
 */
@Controller
@ResponseBody
public class HelloController {

    @RequestMapping("/hello")
    public String test(){
        return "Hello SpringBoot!";
    }
}
