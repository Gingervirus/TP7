package com.example.armin.tp5.repository.Student.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.armin.tp5.conf.databases.DBConstants;
import com.example.armin.tp5.domain.Student.StudentData;
import com.example.armin.tp5.domain.Student.StudentDetails;
import com.example.armin.tp5.repository.Student.StudentRepository;

import java.util.HashSet;
import java.util.Set;



/**
 * Created by Armin on 2016-04-24.
 */
public class StudentRepositoryImpl extends SQLiteOpenHelper implements StudentRepository {
    public static final String TABLE_NAME = "student";
    private SQLiteDatabase db;

    public static final String COLUMN_STUDENT_NR = "studentNr";
    public static final String COLUMN_GRADE= "grader";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_DETENTION = "detention";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CELL = "cellphoneNr";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_STUDENT_NR + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GRADE + " TEXT NOT NULL , "
            + COLUMN_MARKS + " TEXT NOT NULL , "
            + COLUMN_DETENTION + " BOOLEAN NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_CELL + " TEXT NOT NULL );";


    public StudentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public StudentData findById(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_STUDENT_NR,
                        COLUMN_GRADE,
                        COLUMN_MARKS,
                        COLUMN_DETENTION,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_DATE_OF_BIRTH,
                        COLUMN_GENDER,
                        COLUMN_CELL},
                COLUMN_STUDENT_NR + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final StudentDetails studentDetails = new StudentDetails.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                    .build();
            final StudentData studentData = new StudentData.Builder()
                    .studNr(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NR)))
                    .grade(cursor.getString(cursor.getColumnIndex(COLUMN_GRADE)))
                    .marks(cursor.getString(cursor.getColumnIndex(COLUMN_MARKS)))
              //      .detention(cursor.getType(cursor.getColumnIndex(COLUMN_DETENTION)))
                    .studentDetails(studentDetails)
                    .build();

            return studentData;
        } else {
            return null;
        }
    }

    @Override
    public StudentData save(StudentData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NR, entity.getStudNr());
        values.put(COLUMN_GRADE, entity.getGrade());
        values.put(COLUMN_MARKS, entity.getMarks());
        values.put(COLUMN_DETENTION, entity.getdetention());
        values.put(COLUMN_FIRSTNAME, entity.getStudentDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getStudentDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getStudentDetails().getDob());
        values.put(COLUMN_GENDER, entity.getStudentDetails().getGender());
        values.put(COLUMN_CELL, entity.getStudentDetails().getCell());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        StudentData insertedEntity = new StudentData.Builder()
                .copy(entity)
                .studNr(new String(id + ""))
                .build();
        return insertedEntity;
    }

    @Override
    public StudentData update(StudentData entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NR, entity.getStudNr());
        values.put(COLUMN_GRADE, entity.getGrade());
        values.put(COLUMN_MARKS, entity.getMarks());
        values.put(COLUMN_DETENTION, entity.getdetention());
        values.put(COLUMN_FIRSTNAME, entity.getStudentDetails().getName());
        values.put(COLUMN_LASTNAME, entity.getStudentDetails().getLastName());
        values.put(COLUMN_DATE_OF_BIRTH, entity.getStudentDetails().getDob());
        values.put(COLUMN_GENDER, entity.getStudentDetails().getGender());
        values.put(COLUMN_CELL, entity.getStudentDetails().getCell());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_STUDENT_NR + " =? ",
                new String[]{String.valueOf(entity.getStudNr())}
        );
        return entity;
    }

    @Override
    public StudentData delete(StudentData entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_STUDENT_NR + " =? ",
                new String[]{String.valueOf(entity.getStudNr())});
        return entity;
    }

    @Override
    public Set<StudentData> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<StudentData> StudentDatas = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final StudentDetails studentDetails = new StudentDetails.Builder()
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .dob(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_OF_BIRTH)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .cell(cursor.getString(cursor.getColumnIndex(COLUMN_CELL)))
                        .build();
                final StudentData studentData = new StudentData.Builder()
                        .studNr(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NR)))
                        .grade(cursor.getString(cursor.getColumnIndex(COLUMN_GRADE)))
                        .marks(cursor.getString(cursor.getColumnIndex(COLUMN_MARKS)))
                                //      .detention(cursor.getType(cursor.getColumnIndex(COLUMN_DETENTION)))
                        .studentDetails(studentDetails)
                        .build();
                StudentDatas.add(studentData);
            } while (cursor.moveToNext());
        }
        return StudentDatas;
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
