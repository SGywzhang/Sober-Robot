package com.nus.cs5224.visual;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.Arrays;

public class test {
    public static void main(String[] args) throws Exception {
        ResourceLoader loader = new DefaultResourceLoader();
        IamOptions options = new IamOptions.Builder()
                .apiKey("7Lr6c5MVWkfsdM9P4L4aG2agWLz40x-PUBpTNNBa8IVQ")
                .build();

        VisualRecognition service = new VisualRecognition("2018-03-19", options);

        InputStream imagesStream = loader.getResource("classpath:img/img50_3.png").getInputStream();
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .addClassifierId("DefaultCustomModel_534530996")
                .imagesFile(imagesStream)
                .imagesFilename("fruitbowl.jpg")
                .threshold((float) 0.6)
                .owners(Arrays.asList("me"))
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        System.out.println(result);
    }
}
