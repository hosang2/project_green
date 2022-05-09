package com.group3.project_green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class ListController {

    @GetMapping("/")
    public String goChat(){
        return "/list";
    }

    @GetMapping("/food")
    public String goList(Model model){

        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/list";
        
    }

    @GetMapping("/landmark")
    public String goRead(){

        // 모델에 관광지 관련된 리스트를 실어서 보냅니다.
        return "/list";
    }

    @GetMapping("acom")
    public String goAcom(){

        // 모델에 숙박과 관려된 리스트를 실어서 보냅니다..
        return "/acom";
    }




}
