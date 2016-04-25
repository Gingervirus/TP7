package com.example.armin.tp5.repository.Employee.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.armin.tp5.conf.databases.DBConstants;
import com.example.armin.tp5.domain.Employee.EmployeeData;
import com.example.armin.tp5.domain.Employee.EmployeeDetails;
import com.example.armin.tp5.repository.Employee.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Armin on 2016-04-24.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {
    public static final String TABLE_NAME = "employee";
    private SQLiteDatabase db;

    public static final String COLUMN_EMPLOYEE_NR = "empNr";
    public static final String COLUMN_SARS_NR = "sarsNr";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_WORKHOURS= "workingHours";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CELL = "cellphoneNr";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_EMPLOYEE_NR + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SARS_NR  + " TEXT NOT NULL , "
            + COLUMN_SALARY + " TEXT NOT NULL , "
            + COLUMN_WORKHOURS + " TEXT NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_CELL + " TEXT NOT NULL );";


    public EmployeeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public EmployeeData findById(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_EMPLOYEE_NR ,
                        COLUMN_SARS_NR,
                        COLUMN_SALARY,
                        COLUMN_WORKHOURS,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_DATE_OF_BIRTH,
                        COLUMN_GENDER,
                        COLUMN_CELL},
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final EmployeeDetails employeeDetails = new EmployeeDetails.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                    .build();
            final EmployeeData employeeData = new EmployeeData.Builder()
                    .empNr(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_NR )))
                    .sarsNr(cursor.getString(cursor.getColumnIndex(COLUMN_SARS_NR)))
                    .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                    .workingHours(cursor.getString(cursor.getColumnIndex(COLUMN_WORKHOURS)))
                    .employeeDetails(employeeDetails)
                    .build();

            return employeeData;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeData save(EmployeeData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NR , entity.getEmpNr());
        values.put(COLUMN_SARS_NR, entity.getSarsNr());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_WORKHOURS, entity.getWorkingHours());
        values.put(COLUMN_FIRSTNAME, entity.getDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getDetails().getDob());
        values.put(COLUMN_GENDER, entity.getDetails().getGender());
        values.put(COLUMN_CELL, entity.getDetails().getCell());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        EmployeeData insertedEntity = new EmployeeData.Builder()
                .copy(entity)
                .empNr(new String(id + ""))
                .build();
        return insertedEntity;
    }

    @Override
    public EmployeeData update(EmployeeData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NR , entity.getEmpNr());
        values.put(COLUMN_SARS_NR, entity.getSarsNr());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_WORKHOURS, entity.getWorkingHours());
        values.put(COLUMN_FIRSTNAME, entity.getDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getDetails().getDob());
        values.put(COLUMN_GENDER, entity.getDetails().getGender());
        values.put(COLUMN_CELL, entity.getDetails().getCell());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(entity.getEmpNr())}
        );
        return entity;
    }

    @Override
    public EmployeeData delete(EmployeeData entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_EMPLOYEE_NR  + " =? ",
                new String[]{String.valueOf(entity.getEmpNr())});
        return entity;
    }

    @Override
    public Set<EmployeeData> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<EmployeeData> employeeDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final EmployeeDetails employeeDetails = new EmployeeDetails.Builder()
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                        .build();
                final EmployeeData employeeData = new EmployeeData.Builder()
                        .empNr(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_NR )))
                        .sarsNr(cursor.getString(cursor.getColumnIndex(COLUMN_SARS_NR)))
                        .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                        .workingHours(cursor.getString(cursor.getColumnIndex(COLUMN_WORKHOURS)))
                        .employeeDetails(employeeDetails)
                        .build();
                employeeDatas.add(employeeData);
            } while (cursor.moveToNext());
        }
        return employeeDatas;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
