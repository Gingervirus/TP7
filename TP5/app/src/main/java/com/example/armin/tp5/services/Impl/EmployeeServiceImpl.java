package com.example.armin.tp5.services.Impl;

import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.domain.Employee.EmployeeDetails;
import com.example.armin.tp5.factories.SalaryFactory;
import com.example.armin.tp5.factories.WorkingHoursFactory;
import com.example.armin.tp5.services.EmployeeServices;


/**
 * Created by Armin on 2016-04-17.
 */
public class EmployeeServiceImpl implements EmployeeServices
{
    public EmployeeData getEmployee()
    {
        EmployeeDetails details = new EmployeeDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .cell("0764805005")
                .dob("21/06/1995")
                .build();
        EmployeeData emp = new EmployeeData.Builder()
                .empNr("TEA11111")
                .salary(Double.parseDouble(SalaryFactory.getSalary("TEA11111")))
                .workingHours(WorkingHoursFactory.getWorkingHours("Teacher"))
                .sarsNr("2314565346")
                .employeeDetails(details)
                .build();
        return emp;
    }
}
