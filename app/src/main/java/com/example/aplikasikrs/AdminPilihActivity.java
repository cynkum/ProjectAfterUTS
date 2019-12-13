package com.example.aplikasikrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPilihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pilih);

        Button btnLogOutAdmin = (Button) findViewById(R.id.btnLogOutAdmin);

        btnLogOutAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminPilihActivity.this);

                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminPilihActivity.this, "Tidak jadi Log Out", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = AdminPilihActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                        String statusLogin = prefs.getString("isLogin",null);
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putString("isLogin", null);
                        edit.commit();
                        Intent i = new Intent(AdminPilihActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    public void DaftarMatkul(View view){
        Intent i = new Intent(AdminPilihActivity.this,RecyclerViewKrsActivity.class);
        startActivity(i);
    }
    public void DaftarMhs(View view){
        Intent i = new Intent(AdminPilihActivity.this,RecyclerViewMhsActivity.class);
        startActivity(i);
    }
    public void DaftarDosen(View view){
        Intent i = new Intent(AdminPilihActivity.this,RecyclerViewDosenActivity.class);
        startActivity(i);
    }
    public void EditKrs(View view){
        Intent i = new Intent(AdminPilihActivity.this,EditKrsActivity.class);
        startActivity(i);
    }
    public void DataDiri(View view){
        Intent i = new Intent(AdminPilihActivity.this,DataDiriDosenActivity.class);
        startActivity(i);
    }
}
