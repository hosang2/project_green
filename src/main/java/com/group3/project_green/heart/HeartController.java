package com.group3.project_green.heart;

import com.group3.project_green.Session.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@Log4j2
public class HeartController {
    private final HeartService service;

    @PostMapping("")
    public ResponseEntity<Long> saveHeart(@RequestBody HeartDTO dto,@AuthenticationPrincipal SessionUser user) {
        log.info("=====================" + dto + "====================");
        Heart exist = service.getHeart(user.getId(), dto.getPno());
        Long hno = null;
        if (exist == null) {
            hno = service.saveHeart(dto);
        }
        return new ResponseEntity<>(hno, HttpStatus.OK);
    }

    @GetMapping(value = "/{pno}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count(@PathVariable("pno") Long pno) {
        log.info("==========================" + pno + "====================");
        Long count = service.countHeart(pno);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(value = "/{pno}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Heart> getHeartInfo(@PathVariable("pno") Long pno,@AuthenticationPrincipal SessionUser user) {
        Heart exist = service.getHeart(user.getId(), pno);
        if(exist != null) {
            return new ResponseEntity<>(exist, HttpStatus.OK);
        }else return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @DeleteMapping("/{hno}/{pno}/remove")
    public ResponseEntity<Boolean> remove(@PathVariable("hno") Long hno,@PathVariable("pno") Long pno, @AuthenticationPrincipal SessionUser user) {
        log.info("===================" + hno + "====================");
        boolean result = false;
        Heart exist = service.getHeart(user.getId(),pno);
        if(exist != null) {
            service.remove(hno);
            result = true;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
