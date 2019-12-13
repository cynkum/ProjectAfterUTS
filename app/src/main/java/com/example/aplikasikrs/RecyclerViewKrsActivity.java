package com.example.aplikasikrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasikrs.Adapter.DosenAdapter;
import com.example.aplikasikrs.Adapter.KRSAdapter;
import com.example.aplikasikrs.Model.Dosen;
import com.example.aplikasikrs.Model.KRS;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewKrsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KRSAdapter krsAdapter;
    private ArrayList<KRS> krsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_krs);

        addData();

        recyclerView = findViewById(R.id.rvKrs);
        krsAdapter = new KRSAdapter(krsArrayList);
        List<KRS> krsList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewKrsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(krsAdapter);
    }
    public void editKrs(View view){
        Intent i = new Intent(RecyclerViewKrsActivity.this,EditMatkulAdminActivity.class);
        startActivity(i);
    }
    private void addData(){
        krsArrayList = new ArrayList<>();
        krsArrayList.add(new KRS("SI123 - PEMROGRAMAN MOBILE", "Rabu", "4",
                "3","Argo Wibowo", "40"));
        krsArrayList.add(new KRS("SI234 - KONSEP SI", "Senin", "1",
                "3", "Argo Wibowo", "40"));
        krsArrayList.add(new KRS("SI345 - PENGANTAR SI", "Selasa", "2",
                "3","Umi Proboyekti", "38"));
        krsArrayList.add(new KRS("SI456 - BAHASA INDONESIA", "Kamis", "2",
                "3","Umi Proboyekti", "41"));
        krsArrayList.add(new KRS("SI567 - MATEMATIKA SI", "Senin", "4",
                "3","Jong Jek Siang", "50"));

    }
}
