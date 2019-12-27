package com.example.studentinfo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentInfo extends AppCompatActivity {
    dbHandler handler;
    ListView listView;
    TextView adminName, adminEmail;
    SharedPreferences userData;

    ArrayList<student_info_model> dataValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        adminName = findViewById(R.id.username);
        adminEmail = findViewById(R.id.useremail);
        listView = findViewById(R.id.studentList);

        userData = getSharedPreferences("udata", MODE_PRIVATE);
        String admin_name = userData.getString("name",null);
        String admin_email = userData.getString("email",null);

        adminName.setText(admin_name);
        adminEmail.setText(admin_email);


        handler = new dbHandler(StudentInfo.this);
        dataValue = new ArrayList<>();
        dataValue = handler.getStudentInfo();

        studentInfoAdapter adapter = new studentInfoAdapter(StudentInfo.this, dataValue);
        listView.setAdapter(adapter);
    }
}
