package com.group3.project_green.controller;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.Service.CommentService;
import com.group3.project_green.Session.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comments/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody CommentDTO commentDTO) {
        System.out.println(commentDTO);
        Long pno = service.register(commentDTO);
        return new ResponseEntity<>(pno, HttpStatus.OK);
    }

    @GetMapping(value = "/{pno}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDTO>> getListByPost(@PathVariable("pno") Long pno) {
        System.out.println("pno가 controller에  " + pno);
        List<CommentDTO> commentList = service.getList(pno);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @DeleteMapping("/{cno}/{pno}/remove")
    public ResponseEntity<Boolean> remove(@PathVariable("cno") Long cno, @PathVariable("pno") Long pno, @AuthenticationPrincipal SessionUser user) {
        System.out.println(cno);
        System.out.println(pno);
        List<CommentDTO> commentList = service.getList(pno);
        boolean result = false;
        for (int i = 0; i < commentList.size(); i++) {
            if(commentList.get(i).getId().equals(user.getId()) && commentList.get(i).getCno().equals(cno)) {
                service.remove(cno);
                result = true;
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
