package com.nus.cs5224.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class RecognitionResults {
    private List<RecognitionResult> normal;
    private List<RecognitionResult> drunk;
    private List<RecognitionResult> danger;

    public RecognitionResults(List<RecognitionResult> results) {
        normal = new LinkedList<>();
        drunk = new LinkedList<>();
        danger = new LinkedList<>();
        for (RecognitionResult result : results) {
            addResult(result);
        }
        sort();
    }

    private void addResult(RecognitionResult recognitionResult) {
        if (recognitionResult.getClassifyClass().equalsIgnoreCase("normal")) {
            normal.add(recognitionResult);
        } else {
            if (recognitionResult.getScore() > 79) {
                danger.add(recognitionResult);
            } else {
                drunk.add(recognitionResult);
            }
        }
    }

    private void sort() {
        drunk.sort(Comparator.comparing(RecognitionResult::getScore).reversed());
        danger.sort(Comparator.comparing(RecognitionResult::getScore).reversed());
    }
}
