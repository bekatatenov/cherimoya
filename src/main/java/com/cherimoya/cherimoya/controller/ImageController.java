package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.entity.Image;
import com.cherimoya.cherimoya.entity.Post;
import com.cherimoya.cherimoya.service.ImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/load-image", method = RequestMethod.POST)
    public String setImage(@ModelAttribute Image image, @RequestParam(value="image",required=false) MultipartFile file) {
//        Post post = new Post(2,)

//        image.setPost(post);
        try{
            byte[] image64 = Base64.encodeBase64(file.getBytes());
            String result = new String(image64);
            image.setImage(result);
        } catch(Exception e) {
            e.printStackTrace();
        }

        imageService.save(image);
        return "hello";
    }

}
