package com.backenddev3.backenddev3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    
    @GetMapping("")
    public String getMethodName() {
        return "Server Working - test push";
    }
    
}
