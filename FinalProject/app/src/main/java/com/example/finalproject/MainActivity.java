package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    UserDataSource userDataSource;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        userDataSource = new UserDataSource(this);
        if (!userDataSource.isOpen()) userDataSource.open();
        user=new User();
        if(userDataSource.getUserCount()==0)
        {
            Intent intent = new Intent(getApplicationContext(), register.class);
            startActivity(intent);
        }
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

    public void login(View view) {
        if(username.getText().toString().equals(""))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Enter your username",Toast. LENGTH_SHORT);
            toast.show();
        }
        else if(password.getText().toString().equals(""))
        {
            Toast toast=Toast. makeText(getApplicationContext(),"Enter your password",Toast. LENGTH_SHORT);
            toast.show();
        }
        else
            {
            boolean checkUser = userDataSource.checkUser(username.getText().toString(), password.getText().toString());
            if (checkUser) {
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                Intent intent = new Intent(getApplicationContext(), cityList.class);
                startActivity(intent);
            }
            else
                {
                Toast toast=Toast. makeText(getApplicationContext(),"Wrong username or password",Toast. LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void register(View view) {
        Intent intent = new Intent(getApplicationContext(), register.class);
        startActivity(intent);
    }
}

