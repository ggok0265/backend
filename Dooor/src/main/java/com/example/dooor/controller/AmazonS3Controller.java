package com.example.dooor.controller;

import com.example.dooor.dto.AwsS3DTO;
import com.example.dooor.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping
    public ResponseEntity<AwsS3DTO> uploadFile(MultipartFile multipartfile){
        return ResponseEntity.ok(awsS3Service.uploadFile(multipartfile));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam String fileName){
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
