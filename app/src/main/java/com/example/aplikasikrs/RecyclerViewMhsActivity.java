package com.example.aplikasikrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Adapter.DosenAdapter;
import com.example.aplikasikrs.Adapter.MahasiswaAdapter;
import com.example.aplikasikrs.Model.DefaultResult;
import com.example.aplikasikrs.Model.Dosen;
import com.example.aplikasikrs.Model.Mahasiswa;
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewMhsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    ProgressDialog progressDialog;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Mahasiswa mhs = mahasiswaArrayList.get(item.getGroupId());
        if(item.getTitle()=="Ubah Data Dosen"){
            //Toast.makeText(this, "Ubah ID" + dosen.getId(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RecyclerViewMhsActivity.this, EditMhsActivity.class);
            intent.putExtra("id_mhs", mhs.getId());
            intent.putExtra("nama", mhs.getNama());
            intent.putExtra("nim", mhs.getNim());
            intent.putExtra("alamat", mhs.getAlamat());
            intent.putExtra("email", mhs.getEmail());
            intent.putExtra("foto", mhs.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle()=="Hapus Data Mahasiswa"){
            progressDialog = new ProgressDialog(RecyclerViewMhsActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    mhs.getId(),
                    "72170101"

            );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewMhsActivity.this, "Berhasil Menghapus Data Mahasiswa", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RecyclerViewMhsActivity.this, RecyclerViewMhsActivity.class);
                    finish();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewMhsActivity.this, "Gagal Menghapus Data Mahasiswa", Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_mhs);
        recyclerView = findViewById(R.id.rvMhs);
        //addData();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Mahasiswa>> call = service.getMahasiswaAll("72170101");
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                progressDialog.dismiss();

                mahasiswaArrayList = response.body();
                recyclerView = (RecyclerView) findViewById(R.id.rvMhs);
                mahasiswaAdapter = new MahasiswaAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewMhsActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mahasiswaAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewMhsActivity.this, "Login Gagal, Coba Lagi",Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerView);



        mahasiswaAdapter = new MahasiswaAdapter(mahasiswaArrayList);
        List<Mahasiswa> mhsList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mahasiswaAdapter);


    }

    public void klikTombol(View view){
        Intent i = new Intent(RecyclerViewMhsActivity.this,EditMhsActivity.class);
        startActivity(i);
    }

//    private void addData() {
//        mahasiswaArrayList = new ArrayList<>();
//        mahasiswaArrayList.add(new Mahasiswa("FRISKA",  "72170157", "friska@si.ukdw.ac.id",
//                "Jl. Nana No. 50", R.drawable.friska));
//        mahasiswaArrayList.add(new Mahasiswa("72170091 - EVA", "eva@si.ukdw.ac.id",
//                "Jl. Nini No. 23", R.drawable.eva));
//        mahasiswaArrayList.add(new Mahasiswa("72170094 - JONA", "jona@si.ukdw.ac.id",
//                "Jl. Nunu No.99", R.drawable.jona));
//        mahasiswaArrayList.add(new Mahasiswa("72170097 - EMMA", "emma@si.ukdw.ac.id",
//                "Jl. Nene No. 89", R.drawable.emma));
//
//    }
}
