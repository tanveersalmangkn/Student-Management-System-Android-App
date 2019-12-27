package com.example.studentinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText adminNameEmail, password;
    Button login;

    dbHandler handler;
    SharedPreferences.Editor adminData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler = new dbHandler(LoginActivity.this);
        adminNameEmail = findViewById(R.id.adminNameEmail);
        password = findViewById(R.id.loginPass);
        login = findViewById(R.id.loginBtn);

        adminData = getSharedPreferences("udata",MODE_PRIVATE).edit();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = adminNameEmail.getText().toString();
                String pass = password.getText().toString();

                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                } else {

                    boolean booleanValue = handler.getAdminLogin(email, pass);
                    Log.i("helo", "data entered" + email + pass);
                    if (booleanValue == true){

                        adminData.putString("email", email);
                        adminData.putString("name", "tanveer");
                        adminData.apply();

                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class );
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please enter valid Email and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
