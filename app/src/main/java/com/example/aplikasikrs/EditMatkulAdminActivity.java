package com.example.aplikasikrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class EditMatkulAdminActivity extends AppCompatActivity {
    String[] item1={"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
    String[] item2={"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_matkul_admin);

        Spinner spinnerHari = findViewById(R.id.spinnerHariAdmin);
        Spinner spinnerSesi = findViewById(R.id.spinnerSesiAdmin);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(EditMatkulAdminActivity.this, android.R.layout.simple_spinner_dropdown_item,item1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHari.setAdapter(aa);

        spinnerHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(EditMatkulAdminActivity.this, "Anda memilih hari " + item1[i],Toast.LENGTH_SHORT).show();//kalo milih bisa
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(EditMatkulAdminActivity.this, "Anda tidak memilih", Toast.LENGTH_SHORT).show();//kalo ga milih juga bisa
            }
        });

        ArrayAdapter<String> bb = new ArrayAdapter<String>(EditMatkulAdminActivity.this, android.R.layout.simple_spinner_dropdown_item,item2);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSesi.setAdapter(bb);

        spinnerSesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(EditMatkulAdminActivity.this, "Anda memilih sesi " + item2[i],Toast.LENGTH_SHORT).show();//kalo milih bisa
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(EditMatkulAdminActivity.this, "Anda tidak memilih", Toast.LENGTH_SHORT).show();//kalo ga milih juga bisa
            }
        });

        Button btnSaveMatkul = (Button) findViewById(R.id.btnSaveMatkulAdmin);
        btnSaveMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMatkulAdminActivity.this);

                builder.setMessage("Menyimpan data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditMatkulAdminActivity.this, "Tidak jadi Diubah", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(EditMatkulAdminActivity.this, RecyclerViewMatkulActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
