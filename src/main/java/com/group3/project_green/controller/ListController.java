package com.group3.project_green.controller;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.MemberService;
import com.group3.project_green.Service.PostService;
import com.group3.project_green.Session.LoginUser;
import com.group3.project_green.Session.SessionUser;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.memberInfo.MemberDetailDTO;
import com.group3.project_green.memberInfo.MemberInfoDTO;
import com.group3.project_green.memberInfo.service.MemberInfoService;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/home/*")
@RequiredArgsConstructor
public class ListController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberService memberService; //민혁
    private final MemberInfoService memberInfoService;

    //    @GetMapping("/")
//    public String goChat(){
//        return "/list";
//    }
    @GetMapping("/list")
    public String goList(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                         @RequestParam(required = false, defaultValue = "") String searchText ,
                         @PageableDefault(size  = 5 ) Pageable pageable) {
        System.out.println("11)  컨트롤러 jpa 전 ");
        Page<Post> list =postService.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        Stream<Post> temp = list.get();
        List<Post> temp2 = temp.collect(Collectors.toList());
        temp2.forEach(i-> System.out.println(i));
        System.out.println("2) controller -------------------------------start select--------------------------------------------");
       // System.out.println(postService.findByTitleContaining(searchText,pageable));
        //System.out.println("-------------------------------end select---------------------------------------------");
       // model.addAttribute("post",postService.getList());
      //  model.addAttribute("searchText" , list);

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
    public void read(Long pno, Model model, @AuthenticationPrincipal SessionUser user) {
        model.addAttribute("result", postService.getPostWithCommentCnt(pno));
        model.addAttribute("user",user);
    }
    @GetMapping("/readCss")
    public void readCss(Long pno, Model model, @AuthenticationPrincipal SessionUser user) {
        model.addAttribute("result", postService.getPostWithCommentCnt(pno));
        model.addAttribute("user",user);
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
    public String memberPostList(Model model,
                                 @AuthenticationPrincipal SessionUser sessionUser,
                                 @RequestParam(required = false, defaultValue = "") Long pno,
                                 @RequestParam(required = false, defaultValue = "") String searchText,
                                 @PageableDefault(size =4) Pageable pageable){
        PostDTO result = postService.get(pno);
        Page<Post> post = postService.getPostListPage(pno,searchText,searchText,pageable);
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());
        int startPage = Math.max(1,post.getPageable().getPageNumber() -4);
        int endPage = Math.min(post.getTotalPages(), post.getPageable().getPageNumber() +4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("result", result);
        model.addAttribute("post",post);
        model.addAttribute("userInfoDetail", memberDetailDTO);
        return "/home/memberPostList";
    }
    @GetMapping("/foodPostList")
    public String foodPostList(Model model,
                               @AuthenticationPrincipal SessionUser sessionUser,
                               @RequestParam(required = false, defaultValue = "") Long pno,
                               @RequestParam(required = false, defaultValue = "") String searchText,
                               @PageableDefault(size =4) Pageable pageable){
        PostDTO result = postService.get(pno);
        Page<Post> post = postService.getPostByFoodFid(pno,searchText,searchText,pageable);
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());
        int startPage = Math.max(1,post.getPageable().getPageNumber() -4);
        int endPage = Math.min(post.getTotalPages(), post.getPageable().getPageNumber() +4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("result", result);
        model.addAttribute("post",post);
        model.addAttribute("userInfoDetail", memberDetailDTO);
        return "/home/foodPostList";
    }
    @GetMapping("/sightsPostList")
    public String sightsPostList(Model model,
                                 @AuthenticationPrincipal SessionUser sessionUser,
                                 @RequestParam(required = false, defaultValue = "") Long pno,
                                 @RequestParam(required = false, defaultValue = "") String searchText,
                                 @PageableDefault(size =4) Pageable pageable){
        PostDTO result = postService.get(pno);
        Page<Post> post = postService.getPostBySights(pno,searchText,searchText,pageable);
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());
        int startPage = Math.max(1,post.getPageable().getPageNumber() -4);
        int endPage = Math.min(post.getTotalPages(), post.getPageable().getPageNumber() +4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("result", result);
        model.addAttribute("post",post);
        model.addAttribute("userInfoDetail", memberDetailDTO);
        return "/home/sightsPostList";
    }
    @GetMapping("/accomPostList")
    public String accomPostList(Model model,
                                @AuthenticationPrincipal SessionUser sessionUser,
                                @RequestParam(required = false, defaultValue = "") Long pno,
                                @RequestParam(required = false, defaultValue = "") String searchText,
                                @PageableDefault(size =4) Pageable pageable){
        PostDTO result = postService.get(pno);
        Page<Post> post = postService.getPostByAccomAid(pno,searchText,searchText,pageable);
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());
        int startPage = Math.max(1,post.getPageable().getPageNumber() -4);
        int endPage = Math.min(post.getTotalPages(), post.getPageable().getPageNumber() +4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("result", result);
        model.addAttribute("post",post);
        model.addAttribute("userInfoDetail", memberDetailDTO);
        return "/home/accomPostList";
    }

    @GetMapping("/insert")
    public String insert() {
        return "/home/insert";
    }
    @PostMapping("/insert")
    public String goinsert(PostDTO postDTO){
        long dummyMno = 1L; //2번회원으로 지정 차후 시큐리티로 변경하면 자동으로 로그인정보 저장되서 생략가능
        postDTO.setMember(Member.builder().id(dummyMno).build());//2번회원으로 지정 차후 시큐리티로 변경하면 자동으로 로그인정보 저장되서 생략가능
        postService.savePost(postDTO);
        return "redirect:list";
    }
    @PostMapping("/login") //민혁
    public String loginPost(String email, String password , RedirectAttributes rttr ){
        System.out.println("email : "+email);
        System.out.println("password : "+password);

        Member login = memberService.login(Member.builder().email(email).password(password).build());

        if(login!=null){
            System.out.println("login: " +login);
            rttr.addFlashAttribute("login",login);
            return  "redirect:list";
        } else{
            return  "error";
        }
    }

    @PostMapping("/signup") //민혁
    public String signup(String email, String password){
        Member member = Member.builder()
                        .email(email)
                        .password(password)
                        .build();

        System.out.println("여기 post 들어오나 ? " +member);
        memberService.saveMember(member);
        return "/home/login";
    }
    @GetMapping("/signup") //민혁
    public String signup() {
        return "/home/signup";
    }
}
