package com.nus.cs5224.service;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.nus.cs5224.model.RecognitionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;

@Service
public class VisualRecognitionService {
    private static final Logger LOG = LoggerFactory.getLogger(VisualRecognitionService.class);
    private VisualRecognition service;

    @PostConstruct
    private void init() {
        IamOptions options = new IamOptions.Builder()
                .apiKey("D5ikqDh4BVy9riMkTP86fUjsT5HhBccAfP89Dv_mvgOt")
                //.apiKey("7Lr6c5MVWkfsdM9P4L4aG2agWLz40x-PUBpTNNBa8IVQ")
                .build();
        service = new VisualRecognition("2018-03-19", options);
    }

    public RecognitionResult classify(MultipartFile imageFile) {
        try {
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    //.addClassifierId("DefaultCustomModel_534530996")
                    .addClassifierId("Two_class_model_569752745")
                    .imagesFile(imageFile.getInputStream())
                    .imagesFilename(imageFile.getOriginalFilename())
                    .threshold((float) 0.1)
                    .owners(Collections.singletonList("me"))
                    .build();
            ClassifiedImages classifiedImages = service.classify(classifyOptions).execute();
            ClassResult result = classifiedImages.getImages().get(0).getClassifiers().get(0).getClasses().get(0);
            LOG.info("{} | {}", imageFile.getOriginalFilename(), result.getClassName());
            return new RecognitionResult(imageFile.getBytes(), imageFile.getOriginalFilename(), result.getClassName(), new BigDecimal(result.getScore() * 100).setScale(2, RoundingMode.FLOOR).floatValue());
        } catch (Exception e) {
            return null;
        }
    }
}
