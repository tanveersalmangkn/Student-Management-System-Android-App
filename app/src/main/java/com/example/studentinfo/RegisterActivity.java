package com.example.studentinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button register, login;
    EditText adminName, email, pass, confirmPass, number;
    dbHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        handler = new dbHandler(RegisterActivity.this);

        login = findViewById(R.id.backLogin);
        register = findViewById(R.id.btnRegister);

        adminName = findViewById(R.id.adminName);
        email = findViewById(R.id.registerEmail);
        pass = findViewById(R.id.registerPass);
        confirmPass = findViewById(R.id.confirmPass);
        number = findViewById(R.id.registerNumber);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = adminName.getText().toString();
                String adminEmail = email.getText().toString();
                String adminNumber = number.getText().toString();
                String adminPass = pass.getText().toString();
                String adminConfirmPass = confirmPass.getText().toString();

                if (name.isEmpty() || adminEmail.isEmpty() || adminPass.isEmpty() || adminConfirmPass.isEmpty() || adminNumber.isEmpty()){
                    /*adminName.setError("Please Enter your Name");
                    email.setError("Please Enter your Email");
                    pass.setError("Please Set a Password");
                    confirmPass.setError("Fill the field");
                    number.setError("Please enter your number");*/
                    Toast.makeText(RegisterActivity.this, "All the fields should be filled", Toast.LENGTH_SHORT).show();
                } else {

                    if (adminPass.equals(adminConfirmPass)){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                        handler.setAdminRegistration(name, adminEmail, adminNumber, adminPass);

                        Intent disp = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(disp);
                    }
                    else {

                        Toast.makeText(RegisterActivity.this, "Try Again, Password not matched", Toast.LENGTH_SHORT).show();
                    }

                }

             }
        });






    }
}
