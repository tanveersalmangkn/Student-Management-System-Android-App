package com.example.studentinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class StudentUpdateDetailsActivity extends AppCompatActivity {
    String ids;
    String names, classes, ages, regnos, cells, genders;

    String gender;
    dbHandler handler;

    EditText uName, uClass, uAge, uRegno, uCell, uAddress;
    RadioButton uMale, uFemale;
    Button updateIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update_details);

        uName = findViewById(R.id.updateStudentName);
        uClass = findViewById(R.id.updateStudentClass);
        uAge = findViewById(R.id.updateStudentAge);
        uRegno = findViewById(R.id.updateStudentRegno);
        uCell = findViewById(R.id.updateStudentCell);
        uMale = findViewById(R.id.updateMale);
        uAddress = findViewById(R.id.updateStudentAddress);
        uFemale = findViewById(R.id.updateFemale);

        updateIt = findViewById(R.id.updateIt);


        handler = new dbHandler(StudentUpdateDetailsActivity.this);


        ids = getIntent().getStringExtra("ids");
        names = getIntent().getStringExtra("stname");
        classes = getIntent().getStringExtra("stclass");
        ages = getIntent().getStringExtra("stage");
        regnos = getIntent().getStringExtra("stregno");
        cells = getIntent().getStringExtra("stcell");
        genders = getIntent().getStringExtra("stgender");

        uName.setText(names);
        uClass.setText(classes);
        uAge.setText(ages);
        uRegno.setText(regnos);
        uCell.setText(cells);
       /* if (genders.equals("Male")){
            uMale.setChecked(true);
            uFemale.setChecked(false);
        }else{
            uFemale.setChecked(true);
            uMale.setChecked(false);
        }
*/
        uMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uMale.isChecked()){
                    gender = "Male";
                }
            }
        });
        uFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uFemale.isChecked()){
                    gender = "Female";
                }
            }
        });
        updateIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_name = uName.getText().toString();
                String student_class = uClass.getText().toString();
                String student_age = uAge.getText().toString();
                String student_regno = uRegno.getText().toString();
                String student_cell = uCell.getText().toString();

                if (student_name.isEmpty() || student_class.isEmpty() || student_age.isEmpty() || student_regno.isEmpty() ||student_cell.isEmpty()){
                    Toast.makeText(StudentUpdateDetailsActivity.this, "Please fill in all the registration fields", Toast.LENGTH_SHORT).show();
                } else {
                    handler.studentUpdateDetails(Integer.parseInt(ids), student_name, student_age, student_class, student_regno,
                            gender,student_cell);
                    Toast.makeText(StudentUpdateDetailsActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
