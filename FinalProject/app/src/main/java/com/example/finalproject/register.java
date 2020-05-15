package com.example.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmPassword;
    Button register;
    UserDataSource userDataSource;
    User user;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);
        userDataSource = new UserDataSource(this);
        if (!userDataSource.isOpen()) userDataSource.open();
        user = new User();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!userDataSource.isOpen()) userDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public void add(View view) {
        if(username.getText().toString().equals(""))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Enter your username",Toast. LENGTH_SHORT);
            toast.show();
        }
        else if(userDataSource.checkUsername(username.getText().toString()))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Username already exists",Toast. LENGTH_SHORT);
            toast.show();
        }
        else if (password.getText().toString().equals(""))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Enter your password",Toast. LENGTH_SHORT);
            toast.show();
        }
        else if(confirmPassword.getText().toString().equals(""))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Confirm your password",Toast. LENGTH_SHORT);
            toast.show();
        }
        else if(!password.getText().toString().equals(confirmPassword.getText().toString()))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Your password doesn't match.",Toast. LENGTH_SHORT);
            toast.show();
        }
        else
            {
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setId(userDataSource.getUserCount() + 1);
            userDataSource.addUser(user);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}

