package com.zsun;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:WorldController
 * Package:com.zsun.springboot
 * Description:
 *
 * @author zy
 * @create 2023-04-2010:52
 * @Version 1.0
 */
@RestController
public class WorldController {
    @RequestMapping("/world")
    public String test(){
        return "world！";
    }
}
