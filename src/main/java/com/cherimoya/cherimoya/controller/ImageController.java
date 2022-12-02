package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.entity.Image;
import com.cherimoya.cherimoya.service.ImageService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    private ImageService imageService;
    @PostMapping(name = "/load-image")
    public String loadImage( @RequestParam("file")MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile. getSize());
        image.setImage(multipartFile.getBytes());
        this.imageService.save(image);
        return "success upload";
    }
}
