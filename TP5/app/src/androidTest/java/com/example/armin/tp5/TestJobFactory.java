package com.example.armin.tp5;


import com.example.armin.tp5.factories.JobFactory;

import junit.framework.TestCase;

/**
 * Created by Armin on 2016-04-06.
 */
public class TestJobFactory extends TestCase
{

        public void testJobFactory()
        {
            String job = JobFactory.getJob("TEA123123");
            System.out.println("Your job is :" + job);
        }

}
