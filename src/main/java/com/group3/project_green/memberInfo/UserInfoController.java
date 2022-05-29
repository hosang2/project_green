package com.group3.project_green.memberInfo;

import com.group3.project_green.memberInfo.service.MemberInfoService;
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
        MemberInfoDTO memberInfoDTO = memberInfoService.get(1L);

        log.info("=================================memberDTO : " + memberInfoDTO.toString());

        model.addAttribute("userDetail", memberInfoDTO);
    }

    @GetMapping("/userDetailModify")
    void goUserInfoModify(Model model){
        log.info("==================(Get) userDetailModify===============");

        MemberInfoDTO memberInfoDTO = memberInfoService.get(1L);

        log.info("=================================memberDTO : " + memberInfoDTO.toString());

        model.addAttribute("userDetail", memberInfoDTO);
    }

    @PostMapping("/quit")
    String quit(long id){
        log.info("==================(Post) quit=============== id : " + id);

        memberInfoService.quit(id);

        // 회원탈퇴 이후, 로그인 화면으로 리다이렉트
        return "redirect:/home/login";

    }

    @GetMapping("/myPosts")
    String goMyPosts(long id){
        log.info("===================(Get) myPosts============= id : " + id);

        return "redirect:/home/list";

    }

}
