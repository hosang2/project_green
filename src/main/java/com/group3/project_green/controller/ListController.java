package com.group3.project_green.controller;

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

    private final PostRepository postRepository;

//    @GetMapping("/")
//    public String goChat(){
//        return "/list";
//    }
    @GetMapping("/list")
    public String goList(Model model) {

        List<Post> result = postRepository.findAll();

        System.out.println("================");
        System.out.println(result.toString());
        System.out.println("================");
        model.addAttribute("post", result);

        return "/home/list";
    }
    @PostMapping("/food")
    public String gofood(Model model ){

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
