package com.group3.project_green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/*")
public class ListController {

//    @GetMapping("/")
//    public String goChat(){
//        return "/list";
//    }
    @GetMapping("/list")
    public String goList(Model model) {

        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/home/list";
    }
    /*@GetMapping("/food")
    public String gofood(Model model){

        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/home/list";
        
    }

    @GetMapping("/landmark")
    public String goRead(){

        // 모델에 관광지 관련된 리스트를 실어서 보냅니다.
        return "/home/list";
    }

    @GetMapping("acom")
    public String goAcom(){

        // 모델에 숙박과 관려된 리스트를 실어서 보냅니다..
        return "/home/list";
    }   get 필요없을듯  01048609771 << 전화  */

    @GetMapping("/login")
    public String login() {

        return "/home/login";
    }
    @GetMapping("/userinfo")
    public String userinfo() {

        return "/home/userinfo";
    }



}
