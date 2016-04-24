package com.example.armin.tp5;

import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.domain.Employee.EmployeeDetails;
import com.example.armin.tp5.factories.SalaryFactory;
import com.example.armin.tp5.factories.WorkingHoursFactory;

import junit.framework.TestCase;

/**
 * Created by Armin on 2016-04-06.
 */
public class TestEmployee extends TestCase {
    public void testPerson() throws Exception {

        EmployeeDetails details = new EmployeeDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .cell("0764805005")
                .dob("21/06/1995")
                .build();
        EmployeeData person = new EmployeeData.Builder()
                .empNr("TEA11111")
                .salary(Double.parseDouble(SalaryFactory.getSalary("TEA11111")))
                .workingHours(WorkingHoursFactory.getWorkingHours("Teacher"))
                .sarsNr("2314565346")
                .employeeDetails(details)
                .build();

        assertEquals(person.getDetails().getDob(), "21/06/1995");
        assertEquals(person.getDetails().getName(), "Armin");
        assertEquals(person.getEmpNr(), "TEA11111");
        assertEquals(person.getDetails().getLastName(), "Wentzel");
        assertEquals(person.getDetails().getGender(), "Male");
        assertEquals(person.getSalary(), 15000.00);
        assertEquals(person.getWorkingHours(), "08H00-13H00");
        assertEquals(person.getSarsNr(), "2314565346");
        assertEquals(person.getDetails().getCell(), "0764805005");

        EmployeeData newPerson = new EmployeeData.Builder()
                .copy(person)
                .empNr("123123123")
                .build();

        assertEquals(newPerson.getEmpNr(), "123123123");
    }
}
