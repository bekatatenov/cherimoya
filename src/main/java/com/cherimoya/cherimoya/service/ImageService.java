package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.ImageRepository;
import com.cherimoya.cherimoya.entity.Image;
import net.minidev.asm.ex.NoSuchFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void save(MultipartFile multipartFile) {
//        multipartFile.
    }

    public void delete(long image){
        imageRepository.delete(imageRepository.findById(image).orElseThrow(() -> new NoSuchFieldException("gh")));
    }



}
