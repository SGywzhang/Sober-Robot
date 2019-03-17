package com.nus.cs5224.service;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.nus.cs5224.model.RecognitionResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class VisualRecognitionService {

    private VisualRecognition service;

    @PostConstruct
    private void init() {
        IamOptions options = new IamOptions.Builder()
                .apiKey("7Lr6c5MVWkfsdM9P4L4aG2agWLz40x-PUBpTNNBa8IVQ")
                .build();

        service = new VisualRecognition("2018-03-19", options);
    }

    public RecognitionResult classify(MultipartFile imageFile) {
        try {
            ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .addClassifierId("DefaultCustomModel_534530996")
                    .imagesFile(imageFile.getInputStream())
                    .imagesFilename(imageFile.getOriginalFilename())
                    .threshold((float) 0.6)
                    .owners(Collections.singletonList("me"))
                    .build();
            ClassifiedImages classifiedImages = service.classify(classifyOptions).execute();
            ClassResult result = classifiedImages.getImages().get(0).getClassifiers().get(0).getClasses().get(0);
            return new RecognitionResult(imageFile.getBytes(), imageFile.getOriginalFilename(), result.getClassName(), result.getScore());
        } catch (Exception e) {
            return null;
        }
    }
}
