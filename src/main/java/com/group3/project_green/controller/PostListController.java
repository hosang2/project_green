package com.group3.project_green.controller;

import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.Service.PostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homelist/")
@RequiredArgsConstructor
public class PostListController {
    private  final PostListService service;

    @GetMapping(value = "/allList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getAllList(){
        List<PostDTO> postDTOList =service.getList();
        System.out.println(postDTOList);
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/accomList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getAccomList(){
        System.out.println("Rest controller 에서 호출 숙소리스트 1 ");
        List<PostDTO> postDTOList =service.getAccomList();
        System.out.println("Rest controller 에서 호출 숙소리스트 2 ");
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/sightList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getSightList(){
        System.out.println("Rest controller 에서 호출 관광지1리스트 ");
        List<PostDTO> postDTOList =service.getSightsList();
        System.out.println("Rest controller 에서 호출 관광지2리스트 ");
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/foodList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getFoodList(){
        System.out.println("Rest controller 에서 호출 음식리스트1 ");
        List<PostDTO> postDTOList =service.getFoodList();
        System.out.println("Rest controller 에서 호출 음식리스트2 ");
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
}
