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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditKrsActivity extends AppCompatActivity {
    String[] items={"Katon Wijana", "Argo Wibowo", "Umi Proboyekti", "Yetli Oslan", "Jong Jek Siang", "Erick Kurniawan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_krs);

        Spinner spinner = findViewById(R.id.spinnerKrs);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(EditKrsActivity.this, android.R.layout.simple_spinner_dropdown_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(EditKrsActivity.this, "Anda memilih dosen " + items[i],Toast.LENGTH_SHORT).show();//kalo milih bisa
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(EditKrsActivity.this, "Anda tidak memilih", Toast.LENGTH_SHORT).show();//kalo ga milih juga bisa
            }
        });

        Button btnSaveKrs = (Button) findViewById(R.id.btnSaveKrs);

        btnSaveKrs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(EditKrsActivity.this);

                builder.setMessage("Menyimpan data?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditKrsActivity.this, "Tidak jadi Diubah", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(EditKrsActivity.this, RecyclerViewKrsActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

}
