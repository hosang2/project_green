package com.group3.project_green.memberInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/userinfo/")
public class MemberInfoRestController {

    private final MemberInfoService memberInfoService;

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> quit(@PathVariable("id") Long id){
        log.info("====================(REST Delete) quit,, id : " + id);

        memberInfoService.quit(id);

        return new ResponseEntity<>("quited completely", HttpStatus.OK);
    }

}
