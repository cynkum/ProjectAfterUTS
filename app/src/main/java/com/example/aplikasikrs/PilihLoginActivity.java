package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PilihLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_login);

        Button btnLoginMhs = (Button) findViewById(R.id.btnLoginMhs);
        Button btnLoginDosen = (Button) findViewById(R.id.btnLoginDosen);
        Button btnLoginAdmin = (Button) findViewById(R.id.btnLoginAdmin);

        btnLoginMhs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(PilihLoginActivity.this,DosenPilihActivity.class);
                startActivity(i);
            }
        });

        btnLoginDosen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(PilihLoginActivity.this,Dosen2PilihActivity.class);
                startActivity(i);
            }
        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(PilihLoginActivity.this,AdminPilihActivity.class);
                startActivity(i);
            }
        });
    }
}
