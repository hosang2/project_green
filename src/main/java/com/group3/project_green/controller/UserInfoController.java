package com.group3.project_green.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userinfo/*")
@RequiredArgsConstructor
public class UserInfoController {


    @GetMapping("/userDetail")
    void goUserInfo(){

    }

}
