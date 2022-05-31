package com.group3.project_green.security;


import com.group3.project_green.Session.SessionUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sequrity/")
public class SecurityTestController {

    @GetMapping("/test")
    public void justMemberPrintTest(@AuthenticationPrincipal SessionUser sessionUser){

        System.out.println("==================sessionUser : " + sessionUser.toString());
        System.out.println("==================email : " + sessionUser.getEmail());
        System.out.println("==================password : " + sessionUser.getPassword());
        System.out.println("==================attr : " + sessionUser.getAttr().toString());

    }

}
