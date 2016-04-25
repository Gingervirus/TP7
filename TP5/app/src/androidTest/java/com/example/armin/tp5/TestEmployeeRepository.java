package com.example.armin.tp5;

import android.test.AndroidTestCase;

import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.domain.Employee.EmployeeDetails;
import com.example.armin.tp5.factories.SalaryFactory;
import com.example.armin.tp5.factories.WorkingHoursFactory;
import com.example.armin.tp5.repository.Employee.EmployeeRepository;
import com.example.armin.tp5.repository.Employee.Impl.EmployeeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;


/**
 * Created by Armin on 2016-04-25.
 */
public class TestEmployeeRepository extends AndroidTestCase{

        private static final String TAG="SETTINGS TEST";
        private String id;

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());
        // CREATE

        EmployeeDetails details = new EmployeeDetails.Builder()
                .name("Armin")
                .lastName("Wentzel")
                .gender("Male")
                .cell("0764805005")
                .dob("21/06/1995")
                .build();
        EmployeeData createEntity = new EmployeeData.Builder()
                .empNr("TEA11111")
                .salary(Double.parseDouble(SalaryFactory.getSalary("TEA11111")))
                .workingHours(WorkingHoursFactory.getWorkingHours("Teacher"))
                .sarsNr("2314565346")
                .employeeDetails(details)
                .build();
        EmployeeData insertedEntity = repo.save(createEntity);
        id=insertedEntity.getEmpNr();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<EmployeeData> employeeDatas = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",employeeDatas.size()>0);

        //READ ENTITY
        EmployeeData entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        EmployeeData updateEntity = new EmployeeData.Builder()
                .copy(entity)
                .workingHours("12H00-17H00")
                .build();
        repo.update(updateEntity);
        EmployeeData newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","12H00-17H00",newEntity.getWorkingHours());

        // DELETE ENTITY
        repo.delete(updateEntity);
        EmployeeData deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }


}
