package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.txtUsername);
        login = (Button) findViewById(R.id.btnSignIn);
        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        saveLogin = sharedPreferences.getBoolean("saveLogin", true);
        if (saveLogin == true) {
            username.setText(sharedPreferences.getString("username", null));
        }

    }

    public void login() {
        String usrname = username.getText().toString();
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        if (usrname.contains("@staff.ukdw.ac.id")) {
            editor.putString("isLogin", "Admin");
            editor.commit();
            Intent i = new Intent(MainActivity.this, AdminPilihActivity.class);
            startActivity(i);
        } else if (usrname.contains("@si.ukdw.ac.id")) {
            editor.putString("isLogin","Mhs");
            editor.commit();
            Intent i = new Intent(MainActivity.this, DosenPilihActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
    }
}
