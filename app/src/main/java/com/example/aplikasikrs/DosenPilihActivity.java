package com.example.aplikasikrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Model.Dosen;

public class DosenPilihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_pilih);

        Button btnLogOutMhs = (Button) findViewById(R.id.btnLogOutMhs);

        btnLogOutMhs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DosenPilihActivity.this);

                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DosenPilihActivity.this, "Tidak jadi Log Out", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = DosenPilihActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                        String statusLogin = prefs.getString("isLogin",null);
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putString("isLogin", null);
                        edit.commit();
                        Intent i = new Intent(DosenPilihActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
    public void buttonLihatKelasDosen(View view){
        Intent i = new Intent(DosenPilihActivity.this,RecyclerViewKrsActivity.class);
        startActivity(i);
    }
    public void buttonDataDosen(View view){
        Intent i = new Intent(DosenPilihActivity.this,DataDiriMhsActivity.class);
        startActivity(i);
    }
    public void buttonDaftarKrsDosen(View view){
        Intent i = new Intent(DosenPilihActivity.this,RecyclerViewLihatKelasActivity.class);
        startActivity(i);
    }

}
