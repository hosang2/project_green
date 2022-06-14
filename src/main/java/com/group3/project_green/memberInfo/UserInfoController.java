package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.MemberInfoProfileDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.MemberinfoService;
import com.group3.project_green.Service.PostService;
import com.group3.project_green.Session.SessionUser;
import com.group3.project_green.entity.*;
import com.group3.project_green.memberInfo.service.MemberInfoService;
import com.group3.project_green.memberInfo.service.MyPostListService;
import com.group3.project_green.repository.MemberinfoRepository;
import com.group3.project_green.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/userinfo/*")
@RequiredArgsConstructor
public class UserInfoController {

    private final PostService postService;

    private final MemberInfoService memberInfoService;

    private final MemberinfoService memberinfoService;
    private final MyPostListService myPostListService;

    //프로필 이미지
    private final ProfileImageRepository profileImageRepository;




    @GetMapping("/userDetail")
    void goUserInfo(@AuthenticationPrincipal SessionUser sessionUser, Model model){
        //프로필 이미지
        List<ProfileImage> profileImages = profileImageRepository.findByMemberInfoIno(sessionUser.getId());
        model.addAttribute("img", profileImages);

        log.info("==================(Get) userDetail===============");
        /* 임시로 1번 계정에만 접근합니다.
         * 시큐리티 적용이 완료되면, 아래 코드에서 1L만 세션유저의 ID로 바꿔주면 됩니다.
         * */
        MemberInfoDTO memberInfoDTO = memberInfoService.get(sessionUser.getId());
        MemberDetailDTO memberDetailDTO = memberInfoService.getDetail(sessionUser.getId());


        log.info("=================================memberDTO : " + memberInfoDTO.toString());

        List<PostDTO> post = myPostListService.getListByMemberId(sessionUser.getId());

        Long pno = post.get(0).getPno();

        model.addAttribute("pno", pno);
        model.addAttribute("userDetail", memberInfoDTO);
        model.addAttribute("userInfoDetail", memberDetailDTO);
        //프로필 이미지 모델

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

    @PostMapping("/DetailModify")
    String modifyUserInfo(@AuthenticationPrincipal SessionUser sessionUser,
                          MemberDetailDTO memberDetailDTO){

        log.info("=================detail modify, id : " + sessionUser.getId());
        log.info("=================detail modify, memberDTO : " + memberDetailDTO);

        memberInfoService.modifyInfo(sessionUser.getId(), memberDetailDTO);

        return "redirect:/userinfo/userDetail";
    }

    //프로필이미지 추가
    @PostMapping("/ProfileInsert")
    public String goinsert(MemberInfoProfileDTO memberInfoDTO,
                           @AuthenticationPrincipal SessionUser sessionUser,
                           RedirectAttributes redirectAttributes){
        System.out.println("멤버인포 :"+ memberInfoDTO);
        memberInfoDTO.setIno(sessionUser.getId());
        memberInfoDTO.setMember(Member.builder().id(sessionUser.getId()).build());
        memberinfoService.insert(memberInfoDTO);
        return "redirect:/userinfo/userDetail";
    }
    ;;
}
