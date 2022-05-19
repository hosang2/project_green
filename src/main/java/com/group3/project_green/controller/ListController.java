package com.group3.project_green.controller;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.CommentService;
import com.group3.project_green.Service.PostService;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
       // model.addAttribute("post",postService.getList());
        return "/home/list";
    }
    @PostMapping("/food")
    public String gofood(Model model ){
       // model.addAttribute("food",postService.getListByFood());
        // 모델에 음식에 관련된 리스트를 실어서 보냅니다

        return "/home/list";
    }

    @PostMapping("/landmark")
    public String goRead(Model model){
      // model.addAttribute("sights", postService.getListBysights());
        // 모델에 관광지 관련된 리스트를 실어서 보냅니다.
        return "/home/list";
    }

    @PostMapping("acom")
    public String goAcom(Model model){
    //model.addAttribute("accom", postService.getListByAccom());
        //모델에 숙박과 관려된 리스트를 실어서 보냅니다..
        return "/home/list";
    }

    @GetMapping("/read")
    public void read(Long pno, Model model) {
        PostDTO result = postService.get(pno);
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
