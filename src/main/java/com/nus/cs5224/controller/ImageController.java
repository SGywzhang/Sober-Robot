package com.nus.cs5224.controller;

import com.nus.cs5224.model.RecognitionResult;
import com.nus.cs5224.model.RecognitionResults;
import com.nus.cs5224.service.VisualRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

    private final VisualRecognitionService service;

    @Autowired
    public ImageController(VisualRecognitionService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public RecognitionResults uploadFile(@RequestParam("images") MultipartFile[] images) {
        List<RecognitionResult> results = Arrays.stream(images)
                .parallel()
                .map(service::classify)
                .collect(Collectors.toList());
        return new RecognitionResults(results);
    }
}
