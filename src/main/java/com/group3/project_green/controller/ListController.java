package com.group3.project_green.controller;

import com.group3.project_green.Service.PostService;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
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
    private final PostRepository postRepository;

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
        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/home/list";
    }

    @GetMapping("/sight")
    public String goSight(Model model){
        model.addAttribute("post",postService.getSightsList());
        // 모델에 관광지 관련된 리스트를 실어서 보냅니다.
        return "/home/list";
    }

    @GetMapping("accom")
    public String goAccom(Model model){
        model.addAttribute("post",postService.getAccomList());
        //모델에 숙박과 관려된 리스트를 실어서 보냅니다..
        return "/home/list";
    }

    @GetMapping("/read")
    public void read(Long pno, Model model) {
        Post result = postRepository.getById(pno);
        model.addAttribute("result", result);
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
    public String memberPostList(){

        return "/home/memberPostList";
    }
}
