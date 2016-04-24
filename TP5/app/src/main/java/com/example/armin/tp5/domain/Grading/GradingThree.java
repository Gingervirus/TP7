package com.example.armin.tp5.domain.Grading;

/**
 * Created by Armin on 2016-04-07.
 */
public class GradingThree extends Grading{
    @Override
    public String handleRequest(int request) {
        if (request > 60 & request < 70) {
            return "Pass";
        } else {
            if (nextGrading != null) {
                return nextGrading.handleRequest(request);
            }
            return "No Grade";
        }
    }
}