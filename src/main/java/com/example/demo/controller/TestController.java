package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @PostMapping("/01")
    public void test01(MultipartFile file) {
        var tempFile = toTempFile(file);
        if (tempFile == null) {
            return;
        }

        try {
            var resultFile = new File("/var/temp/001.webp");
            var bf = ImageIO.read(tempFile);
            //https://github.com/sejda-pdf/webp-imageio
            ImageIO.write(bf, "webp", resultFile);
            System.out.println("done!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private File toTempFile(MultipartFile multipartFile) {
        File file;
        try {
            file = File.createTempFile("temp", null);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            file = null;
        }
        return file;
    }

}
