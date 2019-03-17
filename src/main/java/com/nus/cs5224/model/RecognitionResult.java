package com.nus.cs5224.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecognitionResult {
    private byte[] image;
    private String name;
    private String classifyClass;
    private Float score;
}
