package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplikasikrs.Adapter.KRSAdapter;
import com.example.aplikasikrs.Adapter.MatkulAdapter;
import com.example.aplikasikrs.Model.KRS;
import com.example.aplikasikrs.Model.Matkul;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMatkulActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MatkulAdapter matkulAdapter;
    private ArrayList<Matkul> matkulArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_matkul);

        addData();

        recyclerView = findViewById(R.id.rvMatkul);
        matkulAdapter = new MatkulAdapter(matkulArrayList);
        List<Matkul> matkulList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewMatkulActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matkulAdapter);


    }

    private void addData() {
        matkulArrayList = new ArrayList<>();
        matkulArrayList.add(new Matkul("SI123 - PEMROGRAMAN MOBILE", "Rabu", "4",
                "3"));
        matkulArrayList.add(new Matkul("SI234 - KONSEP SI", "Senin", "1",
                "3"));
        matkulArrayList.add(new Matkul("SI345 - PENGANTAR SI", "Selasa", "2",
                "3"));
        matkulArrayList.add(new Matkul("SI456 - BAHASA INDONESIA", "Kamis", "2a",
                "3"));
        matkulArrayList.add(new Matkul("SI567 - MATEMATIKA SI", "Senin", "4",
                "3"));

    }
}
