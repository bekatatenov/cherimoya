//package com.cherimoya.cherimoya.controller;
//
//import com.cherimoya.cherimoya.entity.Document;
//import com.cherimoya.cherimoya.entity.Image;
//import com.cherimoya.cherimoya.entity.User;
//import com.cherimoya.cherimoya.enums.DocumentStatus;
//import com.cherimoya.cherimoya.service.DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//public class DocumentController {
//    @Autowired
//    private DocumentService documentService;
//
//    @GetMapping(value = "/load-page")
//    public String upload(){
//        return "load";
//    }
//
//    @PostMapping(value = "/load")
//    public String upload(@RequestParam User user, @RequestParam MultipartFile image) {
//
//    }
//}
