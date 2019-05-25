package com.hc.hcclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HC
 * @create 2019-05-25 14:04
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "This is Client";
    }
}
