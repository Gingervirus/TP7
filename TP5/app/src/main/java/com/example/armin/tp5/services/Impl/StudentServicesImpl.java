package com.example.armin.tp5.services.Impl;

import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.domain.Student.StudentDetails;
import com.example.armin.tp5.factories.DetentionFactory;
import com.example.armin.tp5.services.StudentServices;

/**
 * Created by Armin on 2016-04-17.
 */
public class StudentServicesImpl implements StudentServices{
    public StudentData getStudent()
    {
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
        return person;
    }
}
