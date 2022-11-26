package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.ImageRepository;
import com.cherimoya.cherimoya.entity.Image;
import net.minidev.asm.ex.NoSuchFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void save(Image image) {
        this.imageRepository.save(image);
    }

    public void delete(long image){
        imageRepository.delete(imageRepository.findById(image).orElseThrow(() -> new NoSuchFieldException("gh")));
    }



}
