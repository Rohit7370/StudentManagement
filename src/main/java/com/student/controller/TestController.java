package com.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/force404")
    public void forceNotFound() throws NoHandlerFoundException {
        throw new NoHandlerFoundException("GET", "/force404", null);
    }
}

