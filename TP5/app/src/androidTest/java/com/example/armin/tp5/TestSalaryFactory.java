package com.example.armin.tp5;


import com.example.armin.tp5.factories.SalaryFactory;

import junit.framework.TestCase;


/**
 * Created by Armin on 2016-04-06.
 */
public class TestSalaryFactory extends TestCase{

    public void testSalaryFactory() throws Exception {

        String salary = SalaryFactory.getSalary("TEA12319");
        System.out.println("The Result is R "+ salary);

    }
}
