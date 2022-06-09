package com.group3.project_green.heart;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@Log4j2
public class HeartController {
    private final HeartService service;

    @PostMapping("")
    public ResponseEntity<Long> saveHeart(@RequestBody HeartDTO dto) {
        log.info("=====================" + dto + "====================");
        Long hno = service.saveHeart(dto);
        return new ResponseEntity<>(hno, HttpStatus.OK);
    }

    @DeleteMapping("/{hno}/remove")
    public ResponseEntity<String> remove(@PathVariable("hno") Long hno) {
        log.info("===================" + hno + "====================");
        service.remove(hno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
