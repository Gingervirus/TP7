package com.example.armin.tp5.domain.Employee;

import java.io.Serializable;

/**
 * Created by Armin on 2016-04-06.
 */
public class EmployeeData implements Serializable {
    private String empNr;
    private String sarsNr;
    private double salary;
    private String workingHours;
    private EmployeeDetails details;
    public EmployeeData(){}

    public  EmployeeData(Builder builder)
    {
        this.empNr = builder.empNr;
        this.sarsNr = builder.sarsNr;
        this.salary = builder.salary;
        this.workingHours = builder.workingHours;
        this.details = builder.details;
    }

    public static class Builder
    {
        private String empNr;
        private String sarsNr;
        private double salary;
        private String workingHours;
        private EmployeeDetails details;

        public Builder sarsNr(String sarsNr)
        {
            this.sarsNr = sarsNr;
            return this;
        }

        public Builder salary(double salary)
        {
            this.salary = salary;
            return this;
        }

        public Builder empNr(String empNr)
        {
            this.empNr = empNr;
            return this;
        }

        public Builder workingHours(String workingHours)
        {
            this.workingHours = workingHours;
            return this;
        }

        public Builder employeeDetails(EmployeeDetails details)
        {
            this.details = details;
            return this;
        }

        public Builder copy(EmployeeData value){
            this.empNr = value.empNr;
            this.sarsNr = value.sarsNr;
            this.salary = value.salary;
            this.workingHours = value.workingHours;
            this.details = value.details;
            return this;
        }

        public EmployeeData build()
        {
            return new EmployeeData(this);
        }
    }



    public String getWorkingHours() {
        return workingHours;
    }

    public String getSarsNr() {
        return sarsNr;
    }

    public EmployeeDetails getDetails() {
        return details;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmpNr() {
        return empNr;
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "empNr='" + empNr + '\'' +
                ", sarsNr='" + sarsNr + '\'' +
                ", salary=" + salary +
                ", workingHours='" + workingHours + '\'' +
                ", details=" + details +
                '}';
    }
}
