package com.example.studentinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StudentRegistration extends AppCompatActivity {
    EditText stname, stage, stclass, stregno, staddress, stcell;
    Button register;
    ImageView imgView;
    String gender;
    public static final int RESULTCODE = 1231;
    RadioButton rbMale, rbFemale;
    String encoded;

    dbHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        dbhandler = new dbHandler(StudentRegistration.this);

        stname = findViewById(R.id.studentName);
        stage = findViewById(R.id.studentAge);
        stclass = findViewById(R.id.studentClass);
        stregno = findViewById(R.id.studentRegno);
        rbMale = findViewById(R.id.male);
        rbFemale = findViewById(R.id.female);
        staddress = findViewById(R.id.studentAddress);
        stcell = findViewById(R.id.studentCell);

        imgView = findViewById(R.id.uploadImage);

        register = findViewById(R.id.stRegister);

        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbMale.isChecked()){
                    gender = "Male";
                    rbFemale.setChecked(false);
                }
            }
        });
        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbFemale.isChecked()){
                    gender = "Female";
                    rbMale.setChecked(false);
                }
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mimType[] = new String[]{"image/jpeg", "image/png"};
                Intent disp = new Intent(Intent.ACTION_PICK);
                disp.setType("image/*");
                disp.putExtra(Intent.EXTRA_MIME_TYPES, mimType);
                startActivityForResult(disp,RESULTCODE);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = stname.getText().toString();
                String studentAge = stage.getText().toString();
                String studentClass = stclass.getText().toString();
                String studentReg = stregno.getText().toString();
                String studentAddress = staddress.getText().toString();
                String studentCell = stcell.getText().toString();


                if (studentName.isEmpty() || studentAge.isEmpty() || studentReg.isEmpty() || studentClass.isEmpty() ||studentAddress.isEmpty()){
                    Toast.makeText(StudentRegistration.this, "Please fill in all the registration fields", Toast.LENGTH_SHORT).show();
                }else{

                    dbhandler.setStudentInfo(studentName, studentAge, studentClass, studentReg, gender, studentAddress, studentCell, encoded);
                    Toast.makeText(StudentRegistration.this, "Student Info Added Successfully", Toast.LENGTH_LONG).show();
                    Log.i("lala", "Info added " + studentName + " " + studentClass + " " + studentAge + " " + studentReg + " " +
                            " " + gender + " " + studentCell);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK){
            Uri images = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), images);
                imgView.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.i("images","" + encoded);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
