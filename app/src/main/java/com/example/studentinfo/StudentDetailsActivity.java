package com.example.studentinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView stname, stclass, stage, stregno, stcell, stgender;
    dbHandler handler;

    String names, classes, ages, regnos, cells, genders;

    ArrayList<student_info_model> datavalues;

    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        stname = findViewById(R.id.showStudentName);
        stclass = findViewById(R.id.showStudentClass);
        stage = findViewById(R.id.showStudentAge);
        stregno = findViewById(R.id.showStudentRegno);
        stcell = findViewById(R.id.showStudentCellno);
        stgender = findViewById(R.id.showStudentGender);
        updateBtn = findViewById(R.id.updateStDetails);


        final String std_id = getIntent().getStringExtra("std_id");
      /*  String std_name = getIntent().getStringExtra("std_name");
        String std_class = getIntent().getStringExtra("std_class");
        String std_age = getIntent().getStringExtra("std_age");
        String std_gender = getIntent().getStringExtra("std_gender");*/

        handler = new dbHandler(StudentDetailsActivity.this);
        datavalues = new ArrayList<>();
        datavalues = handler.getStudentDetails(Integer.parseInt(std_id));

            student_info_model datamodel = datavalues.get(0);

            names = datamodel.getStudent_name();
            classes = datamodel.getStudent_class();
            ages = datamodel.getStudent_age();
            regnos = datamodel.getStudent_registration();
            cells = datamodel.getStudent_cellno();
            genders = datamodel.getStudent_gender();

        Toast.makeText(this, "Student id is " + std_id, Toast.LENGTH_SHORT).show();

        stname.setText(names);
        stclass.setText(classes);
        stage.setText(ages);
        stregno.setText(regnos);
        stcell.setText(cells);
        stgender.setText(genders);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, StudentUpdateDetailsActivity.class);
                intent.putExtra("ids", std_id);
                intent.putExtra("stname", names);
                intent.putExtra("stclass", classes);
                intent.putExtra("stage", ages);
                intent.putExtra("stregno", regnos);
                intent.putExtra("stcell", cells);
                intent.putExtra("stgender", genders);
                startActivity(intent);
            }
        });

    }
}
