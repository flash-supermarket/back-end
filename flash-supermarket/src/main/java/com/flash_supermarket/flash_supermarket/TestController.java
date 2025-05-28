package com.flash_supermarket.flash_supermarket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2025/5/26 11:19
 */

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world wfy";
    }
}

