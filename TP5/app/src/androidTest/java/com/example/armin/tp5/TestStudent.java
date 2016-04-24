package com.example.armin.tp5;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.domain.Student.StudentDetails;
import com.example.armin.tp5.factories.DetentionFactory;

import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * Created by Armin on 2016-04-07.
 */
public class TestStudent extends TestCase{
    public void testStudent() throws Exception {

        StudentDetails stud = new StudentDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .dob("21/06/1995")
                .cell("0764805005")
                .build();
        StudentData person = new StudentData.Builder()
                .studNr("214296725")
                .studentDetails(stud)
                .marks("75#77#82#89#67#69#90")
                .detention(DetentionFactory.getDetention("YES"))
                .grade("3A")
                .build();

        Assert.assertEquals(person.getStudentDetails().getDob(),"21/06/1995");
        Assert.assertEquals(person.getStudentDetails().getName(),"Armin");
        Assert.assertEquals(person.getGrade(),"3A");
        Assert.assertEquals(person.getStudentDetails().getLastName(),"Wentzel");
        Assert.assertEquals(person.getStudentDetails().getGender(),"Male");
        Assert.assertEquals(person.getStudNr(),"214296725");
        Assert.assertEquals(person.getMarks(),"75#77#82#89#67#69#90");
        Assert.assertEquals(person.getStudentDetails().getCell(),"0764805005");

        StudentData newPerson = new StudentData.Builder()
                .copy(person)
                .grade("9")
                .build();

        Assert.assertEquals(newPerson.getGrade(),"9");
    }
}
