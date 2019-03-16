package com.nus.cs5224.service;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

    public ClassifiedImages classify() {
        return null;
    }
}
