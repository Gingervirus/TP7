package com.example.armin.tp5;


import com.example.armin.tp5.factories.WorkingHoursFactory;

import junit.framework.TestCase;

public class TestWorkingHours extends TestCase{

    public void testSalaryFactory() throws Exception {

        String working = WorkingHoursFactory.getWorkingHours("Teacher");
        System.out.println("The Result is "+ working);

    }
}
