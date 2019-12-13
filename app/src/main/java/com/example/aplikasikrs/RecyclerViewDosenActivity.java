package com.example.aplikasikrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Adapter.DosenAdapter;
import com.example.aplikasikrs.Model.DefaultResult;
import com.example.aplikasikrs.Model.Dosen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;



public class RecyclerViewDosenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenArrayList;
    ProgressDialog progressDialog;
    ImageButton imgButDosen;


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenArrayList.get(item.getGroupId());
        if(item.getTitle()=="Ubah Data Dosen"){
            //Toast.makeText(this, "Ubah ID" + dosen.getId(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RecyclerViewDosenActivity.this, EditDosenActivity.class);
            intent.putExtra("id_dosen", dosen.getId());
            intent.putExtra("nama", dosen.getNama());
            intent.putExtra("nidn", dosen.getNidn());
            intent.putExtra("alamat", dosen.getAlamat());
            intent.putExtra("email", dosen.getEmail());
            intent.putExtra("gelar", dosen.getGelar());
            intent.putExtra("foto", dosen.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle()=="Hapus Data Dosen"){
            progressDialog = new ProgressDialog(RecyclerViewDosenActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    dosen.getId(),
                    "72170101"

            );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDosenActivity.this, "Berhasil Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecyclerViewDosenActivity.this, RecyclerViewDosenActivity.class);
                    finish();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDosenActivity.this, "Gagal Menghapus Data Dosen", Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_dosen);
        recyclerView = findViewById(R.id.rvDosen);
        //addData();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170101");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();

                dosenArrayList = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.rvDosen);
                dosenAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDosenActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDosenActivity.this, "Login Gagal, Coba Lagi",Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerView);



        dosenAdapter = new DosenAdapter(dosenArrayList);
        List<Dosen> dosenList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDosenActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dosenAdapter);

    }
    public void klikTombol(View view){
        Intent i = new Intent(RecyclerViewDosenActivity.this,EditDosenActivity.class);
        startActivity(i);
    }

//    private void addData(){
//        dosenArrayList = new ArrayList<>();
//        dosenArrayList.add(new Dosen("1", "010101","12345 - UMI PROBOYEKTI", "S.Kom., MLIS", "othie@staff.ukdw.ac.id",
//                "Jl. Khayalan No. 50", R.drawable.dosencewek1));
//        dosenArrayList.add(new Dosen("2", "020202","23456 - YETLI OSLAN", "S.Kom., M.T", "yetli@staff.ukdw.ac.id",
//                "Jl. Nananina No. 23", R.drawable.dosencewek2));
//        dosenArrayList.add(new Dosen("3", "030303","34567 - KATON WIJANA", "S.Kom., M.T", "katon@staff.ukdw.ac.id",
//                "Jl. Parampam No.99", R.drawable.dosencowok1));
//        dosenArrayList.add(new Dosen("4", "040404","45678 - ARGO WIBOWO", "ST, MT", "argo@staff.ukdw.ac.id",
//                "Jl. Dudududu No. 89", R.drawable.dosencowok2));
//        dosenArrayList.add(new Dosen("5", "050505","56789 - ERICK KURNIAWAN", "S.Kom., M.Kom.", "erick@staff.ukdw.ac.id",
//                "Jl. Cicicuip No. 33", R.drawable.dosencowok3));
//
//    }

}
