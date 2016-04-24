package com.example.armin.tp5;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.factories.MarkGradingFactory;

import junit.framework.TestCase;


/**
 * Created by Armin on 2016-04-07.
 */
public class TestGradingFactory extends TestCase {

    public void testGrading()
    {
        StudentData person = new StudentData.Builder()
                .marks("75#77#82#89#67#69#90")
                .build();

        String marks[] = person.getMarks().split("#");
        String mark;
        for (int i = 0;i < 7; i++)
        {
            mark = MarkGradingFactory.getGrade(Integer.parseInt(marks[i]));
            System.out.println("Mark" + (i+1) + ": " + marks[i] + "You got an " + mark);
        }

    }

}
