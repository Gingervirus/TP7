package com.example.armin.tp5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({TestEmployee.class,
        TestGradingFactory.class, TestJobFactory.class, TestSalaryFactory.class, TestStudent.class, TestWorkingHours.class})
public class UnitTestSuite {}