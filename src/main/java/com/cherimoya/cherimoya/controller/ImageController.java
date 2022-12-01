package com.cherimoya.cherimoya.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @PostMapping(name = "/load-image")
    public String loadImage(@RequestParam("file")MultipartFile multipartFile) {
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile. getSize());
        return "success upload";
    }
}
