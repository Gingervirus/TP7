package com.example.armin.tp5;


import com.example.armin.tp5.factories.SalaryFactory;

import org.junit.Test;


/**
 * Created by Armin on 2016-04-06.
 */
public class TestSalaryFactory{
    @Test
    public void testSalaryFactory() throws Exception {

        String salary = SalaryFactory.getSalary("TEA12319");
        System.out.println("The Result is R "+ salary);

    }
}
