package com.group3.project_green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newCSS/*")
public class newCSSController {

    @GetMapping("/home")
    public void goIndex() {

    }

    @GetMapping("/list")
    public void goMenu() {

    }

    @GetMapping("/about")
    public void goAbout() {

    }

    @GetMapping("/book")
    public void goBook() {

    }

    @GetMapping("/template")
    public void goTemplate() {

    }

}
