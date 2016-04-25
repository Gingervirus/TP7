package com.example.armin.tp5;


import com.example.armin.tp5.factories.WorkingHoursFactory;

import org.junit.Test;

public class TestWorkingHours{
    @Test
    public void testSalaryFactory() throws Exception {

        String working = WorkingHoursFactory.getWorkingHours("Teacher");
        System.out.println("The Result is "+ working);

    }
}
