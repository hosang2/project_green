package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Session.SessionUser;
import com.group3.project_green.memberInfo.service.MemberInfoService;
import com.group3.project_green.memberInfo.service.MyPostListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/userinfo/*")
@RequiredArgsConstructor
public class UserInfoController {

    private final MemberInfoService memberInfoService;

    private final MyPostListService myPostListService;

    @GetMapping("/userDetail")
    void goUserInfo(@AuthenticationPrincipal SessionUser sessionUser, Model model){
        log.info("==================(Get) userDetail===============");

        /* 임시로 1번 계정에만 접근합니다.
        * 시큐리티 적용이 완료되면, 아래 코드에서 1L만 세션유저의 ID로 바꿔주면 됩니다.
        * */
        MemberInfoDTO memberInfoDTO = memberInfoService.get(sessionUser.getId());
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());

        log.info("=================================memberDTO : " + memberInfoDTO.toString());

        model.addAttribute("userDetail", memberInfoDTO);
        model.addAttribute("userInfoDetail", memberDetailDTO);

    }

    @GetMapping("/userDetailModify")
    void goUserInfoModify(@AuthenticationPrincipal SessionUser sessionUser, Model model){
        log.info("==================(Get) userDetailModify===============");

        MemberInfoDTO memberInfoDTO = memberInfoService.get(sessionUser.getId());
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());

        log.info("=================================memberDTO : " + memberInfoDTO.toString());

        model.addAttribute("userDetail", memberInfoDTO);
        model.addAttribute("userInfoDetail", memberDetailDTO);
    }

    @PostMapping("/quit")
    String quit(@AuthenticationPrincipal SessionUser sessionUser){
        log.info("==================(Post) quit=============== id : " + sessionUser.getId());

        memberInfoService.quit(sessionUser.getId());

        // 회원탈퇴 이후, 로그인 화면으로 리다이렉트
        return "redirect:/home/login";

    }

    @GetMapping("/myPosts")
    String goMyPosts(@AuthenticationPrincipal SessionUser sessionUser
            , Model model){
        log.info("===================(Get) myPosts============= id : " + sessionUser.getId());

        List<PostDTO> post = myPostListService.getListByMemberId(sessionUser.getId());

        System.out.println(post.toString());

        model.addAttribute("post", post);

        return "/home/memberPostList";

    }

    @PostMapping("/DetailModify")
    String modifyUserInfo(@AuthenticationPrincipal SessionUser sessionUser,
                          MemberDetailDTO memberDetailDTO){

        log.info("=================detail modify, id : " + sessionUser.getId());
        log.info("=================detail modify, memberDTO : " + memberDetailDTO);

        memberInfoService.modifyInfo(sessionUser.getId(), memberDetailDTO);

        return "redirect:/userinfo/userDetail";
    }

}
