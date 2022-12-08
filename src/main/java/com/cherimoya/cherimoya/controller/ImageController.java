//package com.cherimoya.cherimoya.controller;
//
//import com.cherimoya.cherimoya.entity.Image;
//import com.cherimoya.cherimoya.service.ImageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//
//@Controller
//public class ImageController {
//
//    private ImageService imageService;
//    @PostMapping(name = "/load-image")
//    public String loadImage( @RequestParam("file")MultipartFile multipartFile) throws IOException {
//        if(multipartFile.getContentType().equals("jpeg"));
//        Image image = new Image();
//        multipartFile.
//        image.setWay("/home/akyl/IdeaProjects/cherimoya/src/main/resources/images/");
////        this.imageService.save(image);
//        return "success upload";
//    }
//}
