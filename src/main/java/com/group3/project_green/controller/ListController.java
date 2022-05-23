package com.group3.project_green.controller;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/home/*")
@RequiredArgsConstructor
public class ListController {

    private final PostService postService;

//    @GetMapping("/")
//    public String goChat(){
//        return "/list";
//    }
    @GetMapping("/list")
    public String goList(Model model) {
        model.addAttribute("post",postService.getList());
        return "/home/list";
    }
    @GetMapping("/food")
    public String gofood(Model model ){
        model.addAttribute("post",postService.getFoodList());
       // model.addAttribute("food",postService.getListByFood());
        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/home/list";
    }

    @GetMapping("/sight")
    public String goSight(Model model){
        model.addAttribute("post",postService.getSightsList());

        return "/home/list";
    }

    @GetMapping("accom")
    public String goAccom(Model model){
        model.addAttribute("post",postService.getAccomList());


        return "/home/list";
    }

    @GetMapping("/read")
    public void read(Long pno, Model model) {
        PostCommentDTO result = postService.getPostWithCommentCnt(pno);
        model.addAttribute("result", result);
        System.out.println(result);
    }
    @GetMapping("/insert")
    public String insert() {

        return "/home/insert";
    }

    @GetMapping("/login")
    public String login() {

        return "/home/login";
    }
    @GetMapping("/userinfo")
    public String userinfo() {

        return "/home/userinfo";
    }

    @GetMapping ("/memberPostList")
    public void memberPostList(Long pno, Model model){
        PostDTO result = postService.get(pno);
        model.addAttribute("result", result);
        model.addAttribute("post",postService.getPostList(pno));

    }
}

