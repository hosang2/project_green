package com.group3.project_green.controller;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.Service.MemberInfoService;
import com.group3.project_green.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/userinfo/*")
@RequiredArgsConstructor
public class UserInfoController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/userDetail")
    void goUserInfo(Model model){
        log.info("==================(Get) userDetail===============");

        /* 임시로 1번 계정에만 접근합니다.
        * 시큐리티 적용이 완료되면, 아래 코드에서 1L만 세션유저의 ID로 바꿔주면 됩니다.
        * */
        MemberDTO memberDTO = memberInfoService.get(1L);

        log.info("=================================memberDTO : " + memberDTO.toString());

        model.addAttribute("userDetail", memberDTO);
    }

    @GetMapping("/userDetailModify")
    void goUserInfoModify(Model model){
        log.info("==================(Get) userDetailModify===============");

        MemberDTO memberDTO = memberInfoService.get(1L);

        log.info("=================================memberDTO : " + memberDTO.toString());

        model.addAttribute("userDetail", memberDTO);
    }

    @PostMapping("/quit")
    String quit(long i){
        log.info("==================(Post) quit===============");
        memberInfoService.quit(i);

        return "redirect:/home/login";
    }

}
