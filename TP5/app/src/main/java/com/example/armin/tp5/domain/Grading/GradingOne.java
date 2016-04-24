package com.example.armin.tp5.domain.Grading;

/**
 * Created by Armin on 2016-04-07.
 */
public class GradingOne extends Grading{
    @Override
    public String handleRequest(int request) {
        if(request >= 75){
            return "Destinction";
        }else{
            if (nextGrading!=null) {
                return nextGrading.handleRequest(request);
            }
            return "No Grade";
        }
    }
}