package com.group3.project_green.controller;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.DTO.FileImageDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.CommentService;
import com.group3.project_green.Service.MemberService;
import com.group3.project_green.Service.PostService;
import com.group3.project_green.Session.LoginUser;
import com.group3.project_green.Session.SessionUser;
import com.group3.project_green.entity.*;
import com.group3.project_green.heart.Heart;
import com.group3.project_green.heart.HeartService;
import com.group3.project_green.memberInfo.MemberDetailDTO;
import com.group3.project_green.memberInfo.MemberInfoDTO;
import com.group3.project_green.memberInfo.service.MemberInfoService;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Controller
@RequestMapping("/home/*")
@RequiredArgsConstructor
public class ListController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberService memberService; //민혁
    private final MemberInfoService memberInfoService;

    private final HeartService heartService;
    private final CommentService commentService;

    @GetMapping("/list")
    public String goList(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                         @RequestParam(required = false, defaultValue = "") String searchText ,
                          Pageable pageable) {
        System.out.println("11)  컨트롤러 jpa 전 ");
        List<Post> list =postService.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        model.addAttribute("post",postService.getList());
        model.addAttribute("searchText" , list);

        return "/home/list";
    }
    @GetMapping("/search")
    public String goSearch(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                           @RequestParam(required = false, defaultValue = "") String searchText ,
                           Pageable pageable) {

        List<Post> list =postService.findByTitleContainingOrContentContaining(searchText,searchText,pageable);

        for(Post i : list){
            List<FileImage>images =postService.getImageList(i.getPno());
            FileImage fileImage = images.get(0);
            FileImageDTO imageDTO = new FileImageDTO();
            imageDTO.setImgName(fileImage.getImgName());
            imageDTO.setPath(fileImage.getPath());
            imageDTO.setUuid(fileImage.getUuid());
            List<FileImageDTO> fileImageDTOS =new ArrayList<>();
            fileImageDTOS.add(imageDTO);
            i.getImageDTOList().add(fileImageDTOS.get(0));


            System.out.println("<search : "+ list);
        }

        model.addAttribute("searchText" , list);
        return"home/search";
    }
    @GetMapping("/searchAccom")
    public String goSearchAccom(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                           @RequestParam(required = false, defaultValue = "") String searchText ,
                           Pageable pageable) {
        List<Post> list =postService.accomfindByTitleContainingOrContentContaining(searchText,searchText,pageable);
        for(Post i : list){
            List<FileImage>images =postService.getImageList(i.getPno());
            FileImage fileImage = images.get(0);
            FileImageDTO imageDTO = new FileImageDTO();
            imageDTO.setImgName(fileImage.getImgName());
            imageDTO.setPath(fileImage.getPath());
            imageDTO.setUuid(fileImage.getUuid());
            List<FileImageDTO> fileImageDTOS =new ArrayList<>();
            fileImageDTOS.add(imageDTO);
            i.getImageDTOList().add(fileImageDTOS.get(0));

        }
        model.addAttribute("searchText" , list);
        return"home/search";
    }
    @GetMapping("/searchSight")
    public String goSearchSight(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                           @RequestParam(required = false, defaultValue = "") String searchText ,
                           Pageable pageable) {
        List<Post> list =postService.sightfindByTitleContainingOrContentContaining(searchText,searchText,pageable);
        for(Post i : list){
            List<FileImage>images =postService.getImageList(i.getPno());
            FileImage fileImage = images.get(0);
            FileImageDTO imageDTO = new FileImageDTO();
            imageDTO.setImgName(fileImage.getImgName());
            imageDTO.setPath(fileImage.getPath());
            imageDTO.setUuid(fileImage.getUuid());
            List<FileImageDTO> fileImageDTOS =new ArrayList<>();
            fileImageDTOS.add(imageDTO);
            i.getImageDTOList().add(fileImageDTOS.get(0));

        }
        model.addAttribute("searchText" , list);
        return"home/search";

    }
    @GetMapping("/searchFood")
    public String goSearchFood(Model model, @AuthenticationPrincipal SessionUser sessionUser ,
                           @RequestParam(required = false, defaultValue = "") String searchText ,
                           Pageable pageable) {
        log.info("=================in search=================");
        List<Post> list =postService.foodfindByTitleContainingOrContentContaining(searchText,searchText,pageable);
        for(Post i : list){
            List<FileImage>images =postService.getImageList(i.getPno());
            FileImage fileImage = images.get(0);
            FileImageDTO imageDTO = new FileImageDTO();
            imageDTO.setImgName(fileImage.getImgName());
            imageDTO.setPath(fileImage.getPath());
            imageDTO.setUuid(fileImage.getUuid());
            List<FileImageDTO> fileImageDTOS =new ArrayList<>();
            fileImageDTOS.add(imageDTO);
            i.getImageDTOList().add(fileImageDTOS.get(0));

        }
        model.addAttribute("searchText" , list);
        return"home/search";
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
        Long memberId = user.getId();
        log.info("===========================" + heartService.getHeart(memberId,pno) + "============================");
        Heart heart = heartService.getHeart(memberId,pno);
        Long cnt = heartService.countHeart(pno);
        log.info("===========================" + cnt + "============================");
        if(heart != null){
            log.info("===================================GetHeart가 들어온다=====================================");
            model.addAttribute("heart", heartService.getHeart(memberId,pno));
            model.addAttribute("heartCnt", cnt);
        }
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
    public String goinsert(PostDTO postDTO, @AuthenticationPrincipal SessionUser sessionUser,RedirectAttributes redirectAttributes){
        System.out.println(postDTO);
        if(postDTO.getSelectItem().equals("accom")) {
            Accom accom =new Accom();
            accom.setName(postDTO.getSelectInput());
            postDTO.setAccom(accom);
            postService.saveAccom(accom);
        } else if(postDTO.getSelectItem().equals("food")){
            Food food =new Food();
            food.setName(postDTO.getSelectInput());
            postDTO.setFood(food);
            postService.saveFood(food);
        } else {
            Sights sights = new Sights();
            sights.setName(postDTO.getSelectInput());
            postDTO.setSights(sights);
            postService.saveSights(sights);
        }
        long dummyMno = sessionUser.getId();
        postDTO.setMember(Member.builder().id(dummyMno).build());
        Long pno = postService.insert(postDTO);
        redirectAttributes.addFlashAttribute("psg",pno);
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

    @GetMapping("/main")
    public String mainpage(){
        return "/home/main";
    }
}
