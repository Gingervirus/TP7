package com.example.armin.tp5.domain.Salary;

/**
 * Created by Armin on 2016-04-06.
 */
public class SalarySecretary extends Salary {
    @Override
    public String handleRequest(String request) {
        if (request.equals("SEC")) {
            return "10000.00";
        } else {
            if (nextSalary != null) {
                return nextSalary.handleRequest(request);
            }
            return "Salary does not exist";
        }
    }
}