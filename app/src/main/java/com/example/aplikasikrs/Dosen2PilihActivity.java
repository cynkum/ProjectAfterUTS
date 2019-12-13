package com.example.aplikasikrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dosen2PilihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen2_pilih);

        Button btnLogOutDosen = (Button) findViewById(R.id.btnLogOutDosen);

        btnLogOutDosen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dosen2PilihActivity.this);

                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Dosen2PilihActivity.this, "Tidak jadi Log Out", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Dosen2PilihActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void DaftarMatkulDosen(View view){
        Intent i = new Intent(Dosen2PilihActivity.this,RecyclerViewKrsActivity.class);
        startActivity(i);
    }
    public void DaftarMhsDosen(View view){
        Intent i = new Intent(Dosen2PilihActivity.this,RecyclerViewMhsActivity.class);
        startActivity(i);
    }
    public void DaftarDosenDosen(View view){
        Intent i = new Intent(Dosen2PilihActivity.this,RecyclerViewDosenActivity.class);
        startActivity(i);
    }
    public void EditKrsDosen(View view){
        Intent i = new Intent(Dosen2PilihActivity.this,EditKrsActivity.class);
        startActivity(i);
    }
    public void DataDiriDosen(View view){
        Intent i = new Intent(Dosen2PilihActivity.this,DataDiriDosenActivity.class);
        startActivity(i);
    }
}
