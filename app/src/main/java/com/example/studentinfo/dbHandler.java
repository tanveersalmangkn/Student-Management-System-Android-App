package com.example.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public static final int VERSION = 1;
    public static final String STUDENT_DB = "student_info";

    // Registration Table
    public static final String ADMIN_REGISTRATION_TABLE = "admin_registration";

    //Registration Fields
    public static final String ADMIN_ID = "id";
    public static final String ADMIN_NAME = "name";
    public static final String ADMIN_EMAIL = "email";
    public static final String ADMIN_CELL = "cellno";
    public static final String ADMIN_PASSWORD = "password";

    // Student Registration Fields
    public static final String STUDENT_REGISTRATION_TABLE = "student_registration";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_CLASS = "class_name";
    public static final String STUDENT_REGISTRATION_NUMBER = "registration_number";
    public static final String STUDENT_GENDER = "gender";
    public static final String STUDENT_ADDRESS = "address";
    public static final String STUDENT_CELLNO = "cellno";
    public static final String STUDENT_IMAGE = "img";



    public dbHandler(Context context) {
        super(context, STUDENT_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADMIN_REGISTRATION = "CREATE TABLE " + ADMIN_REGISTRATION_TABLE + " (" +
                ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ADMIN_NAME + " TEXT, " +
                ADMIN_EMAIL + " TEXT, " +
                ADMIN_CELL + " TEXT, " +
                ADMIN_PASSWORD + " TEXT )";
        db.execSQL(CREATE_ADMIN_REGISTRATION);
        Log.i("lala", "created table " + CREATE_ADMIN_REGISTRATION);

        String CREATE_STUDENT_INFO_TABLE = " CREATE TABLE " + STUDENT_REGISTRATION_TABLE + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_NAME + " TEXT, " +
                STUDENT_AGE + " TEXT, " +
                STUDENT_CLASS + " TEXT, " +
                STUDENT_REGISTRATION_NUMBER + " TEXT, " +
                STUDENT_GENDER + " TEXT, " +
                STUDENT_ADDRESS + " TEXT, " +
                STUDENT_CELLNO + " TEXT, " +
                STUDENT_IMAGE + " TEXT )";
        db.execSQL(CREATE_STUDENT_INFO_TABLE);
        Log.i("lala", "Student info table created" + CREATE_STUDENT_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_REGISTRATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_REGISTRATION_TABLE);
        this.onCreate(db);

    }

    public void setAdminRegistration(String name, String email, String cellno, String password) {
        db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ADMIN_NAME, name);
        contentValues.put(ADMIN_EMAIL, email);
        contentValues.put(ADMIN_CELL, cellno);
        contentValues.put(ADMIN_PASSWORD, password);

        db.insert(ADMIN_REGISTRATION_TABLE, null, contentValues);
        Log.i("lalala", "" + contentValues);
        db.close();
    }

    public boolean getAdminLogin(String email, String password) {
        db = this.getWritableDatabase();

        // String qurey = "select * from table_name where admin_email = email and admin_pass = password"

        Cursor cursor = db.rawQuery("select * from " + ADMIN_REGISTRATION_TABLE +
                        " where " + ADMIN_EMAIL + "=? and " + ADMIN_PASSWORD + "=?",
                new String[]{email, password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public void setStudentInfo(String name, String age, String class_name, String registration_number, String gender, String
                               address, String cellno, String image){
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        contentValues.put(STUDENT_AGE, age);
        contentValues.put(STUDENT_CLASS, class_name);
        contentValues.put(STUDENT_REGISTRATION_NUMBER, registration_number);
        contentValues.put(STUDENT_GENDER, gender);
        contentValues.put(STUDENT_ADDRESS, address);
        contentValues.put(STUDENT_CELLNO, cellno);
        contentValues.put(STUDENT_IMAGE, image);

        db.insert(STUDENT_REGISTRATION_TABLE, null, contentValues);
        Log.i("lala", "student info goes here" + contentValues);
        db.close();
    }

    public ArrayList<student_info_model> getStudentInfo(){
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + STUDENT_REGISTRATION_TABLE, null);
        ArrayList<student_info_model> dataValues = new ArrayList<>();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                student_info_model dataModel = new student_info_model();
                dataModel.setStudent_id(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                dataModel.setStudent_name(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                dataModel.setStudent_age(cursor.getString(cursor.getColumnIndex(STUDENT_AGE)));
                dataModel.setStudent_class(cursor.getString(cursor.getColumnIndex(STUDENT_CLASS)));
                dataModel.setStudent_registration(cursor.getString(cursor.getColumnIndex(STUDENT_REGISTRATION_NUMBER)));
                dataModel.setStudent_gender(cursor.getString(cursor.getColumnIndex(STUDENT_GENDER)));
                dataModel.setStudent_address(cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)));
                dataModel.setStudent_cellno(cursor.getString(cursor.getColumnIndex(STUDENT_CELLNO)));
                dataModel.setStudent_img(cursor.getString(cursor.getColumnIndex(STUDENT_IMAGE)));

                dataValues.add(dataModel);
            }
        }
        return dataValues;
    }

    public ArrayList<student_info_model> getStudentDetails(int id){
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + STUDENT_REGISTRATION_TABLE + " where id="+id, null );
        ArrayList<student_info_model> dataValues = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                student_info_model dataModel = new student_info_model();
                dataModel.setStudent_id(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                dataModel.setStudent_name(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                dataModel.setStudent_age(cursor.getString(cursor.getColumnIndex(STUDENT_AGE)));
                dataModel.setStudent_class(cursor.getString(cursor.getColumnIndex(STUDENT_CLASS)));
                dataModel.setStudent_registration(cursor.getString(cursor.getColumnIndex(STUDENT_REGISTRATION_NUMBER)));
                dataModel.setStudent_gender(cursor.getString(cursor.getColumnIndex(STUDENT_GENDER)));
                dataModel.setStudent_address(cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)));
                dataModel.setStudent_cellno(cursor.getString(cursor.getColumnIndex(STUDENT_CELLNO)));
                dataModel.setStudent_img(cursor.getString(cursor.getColumnIndex(STUDENT_IMAGE)));

                dataValues.add(dataModel);
            }
        }
        return dataValues;
    }

    public void studentUpdateDetails(int id, String name, String age, String class_name, String registration_number, String gender, String cellno){
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        contentValues.put(STUDENT_AGE, age);
        contentValues.put(STUDENT_CLASS, class_name);
        contentValues.put(STUDENT_REGISTRATION_NUMBER, registration_number);
        contentValues.put(STUDENT_GENDER, gender);
        contentValues.put(STUDENT_CELLNO, cellno);

        db.update(STUDENT_REGISTRATION_TABLE, contentValues, "id="+id, null );
        Log.i("myvalues" ,"" + contentValues + "data updated" + STUDENT_REGISTRATION_TABLE);
        db.close();

    }

    public int deleteStudentInfo(int id){
        db = this.getWritableDatabase();

        int del = db.delete(STUDENT_REGISTRATION_TABLE, "id=" + id, null);
        return del;
    }

   /* public List<StudentInfoModel> getStudentInfo(){
        db = this.getReadableDatabase();
        List studentList = new ArrayList<StudentInfoModel>();
        String[] column = {STUDENT_ID, STUDENT_EMAIL, STUDENT_AGE, STUDENT_CLASS, STUDENT_CELL};

        Cursor cursor = db.query(STUDENT_REGISTRATION_TABLE, column,null, null, null, null, null);
        if (cursor.getCount() > 0){
            do {
                StudentInfoModel studentInfoModel = new StudentInfoModel();
                studentInfoModel.setStudent_name(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_NAME)));
                studentInfoModel.setStudent_email(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_EMAIL)));
                studentInfoModel.setStudent_age(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_AGE)));
                studentInfoModel.setStudent_class(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_CLASS)));
                studentInfoModel.setStudent_cellno(cursor.getString(cursor.getColumnIndexOrThrow(STUDENT_CELL)));
                studentInfoModel.setStudent_id(cursor.getInt(cursor.getColumnIndexOrThrow(STUDENT_ID)));

                studentList.add(studentList);
            }while (cursor.moveToNext());
        }
        return studentList;
    }*/
}