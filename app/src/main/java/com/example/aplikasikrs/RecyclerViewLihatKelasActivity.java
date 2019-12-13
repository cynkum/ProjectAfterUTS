package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplikasikrs.Adapter.KelasAdapter;
import com.example.aplikasikrs.Adapter.MatkulAdapter;
import com.example.aplikasikrs.Model.Kelas;
import com.example.aplikasikrs.Model.Matkul;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewLihatKelasActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KelasAdapter kelasAdapter;
    private ArrayList<Kelas> kelasArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelas);

        addData();

        recyclerView = findViewById(R.id.rvLihatKelas);
        kelasAdapter = new KelasAdapter(kelasArrayList);
        List<Kelas> kelasList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewLihatKelasActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kelasAdapter);


    }

    private void addData() {
        kelasArrayList = new ArrayList<>();
        kelasArrayList.add(new Kelas("SI123 - PEMROGRAMAN MOBILE", "Rabu", "4",
                "3", "40"));
        kelasArrayList.add(new Kelas("SI234 - KONSEP SI", "Senin", "1",
                "3", "40"));
        kelasArrayList.add(new Kelas("SI345 - PENGANTAR SI", "Selasa", "2",
                "3", "40"));
        kelasArrayList.add(new Kelas("SI456 - BAHASA INDONESIA", "Kamis", "2a",
                "3", "40"));
        kelasArrayList.add(new Kelas("SI567 - MATEMATIKA SI", "Senin", "4",
                "3", "40"));

    }
}
