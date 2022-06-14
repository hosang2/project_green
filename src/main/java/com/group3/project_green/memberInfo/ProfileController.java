package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.ProfileUploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class ProfileController {
    @Value("${part.upload.path}") //application.properties 변수
    private String profilePath;

    private String makeFolder(){
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        //폴더 생성
        File uploadFolder = new File(profilePath, folderPath);
        if(!uploadFolder.exists()) uploadFolder.mkdirs();

        return  folderPath;
    }
    @PostMapping("/profileAjax")
    public ResponseEntity<List<ProfileUploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){
        List<ProfileUploadResultDTO> resulTDtoList = new ArrayList<>();
        for(MultipartFile uploadFile :uploadFiles){
            if(uploadFile.getContentType().startsWith("image") ==false){ //이미지 파일이 아니면
                log.warn("이 파일은 이미지 형태가 아니에요 ");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); //금지되어 있는 것이다
            }
            //실제 파일 이름 IE나 Edge는  전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") +1);
            log.info("fileName: " +fileName);
            //날짜 폴더 생성
            String folderPath = makeFolder();
            //UUID
            String uuid = UUID.randomUUID().toString();
            //저장할 파일 이름 중간에 "_" 를 이용해서 구분
            String saveName = profilePath +File.separator +folderPath +File.separator +uuid+"_" +fileName;
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath); //실제 이미지 저장
                //썸네일 생성
                String thumbnailSaveName = profilePath + File.separator + folderPath + File.separator
                        +"s_" + uuid +"_" + fileName;
                //썸내일 생성
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,100,100);
                resulTDtoList.add(new ProfileUploadResultDTO(fileName,uuid,folderPath));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return  new ResponseEntity<>(resulTDtoList,HttpStatus.OK);
    }
    @GetMapping("/profileDisplay")
    public ResponseEntity<byte[]> getFile(String fileName){
        ResponseEntity<byte[]> result =null;
        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("fileName: " +fileName);
            File file =new File(profilePath+ File.separator+srcFileName);

            log.info("File : " +file);
            HttpHeaders header = new HttpHeaders();
            //MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    @PostMapping("/ProfileRemoveFile")
    public  ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;
        try {
            srcFileName =URLDecoder.decode(fileName,"UTF-8");
            File file = new File(profilePath +File.separator+srcFileName);
            boolean result = file.delete();
            return  new ResponseEntity<>(result,HttpStatus.OK);
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }

}
