package com.example.aplikasikrs.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasikrs.Model.Mahasiswa;
import com.example.aplikasikrs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {//adapter untuk menampung data
    Context context;
    ArrayList<Mahasiswa> mahasiswaArrayList;//isi data dari luar

    public MahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaArrayList) {
        this.mahasiswaArrayList = mahasiswaArrayList; // set data ke array list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_mhs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNama.setText(mahasiswaArrayList.get(position).getNama());
        holder.txtEmail.setText(mahasiswaArrayList.get(position).getEmail());
        holder.txtAlamat.setText(mahasiswaArrayList.get(position).getAlamat());
        holder.txtNim.setText(mahasiswaArrayList.get(position).getNim());
        holder.imgFoto.getLayoutParams().width=300;
        holder.imgFoto.getLayoutParams().height=300;
        if(mahasiswaArrayList.get(position).getFoto() != null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/"+ mahasiswaArrayList.get(position).getFoto())
                    .into(holder.imgFoto);
        }
        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = mahasiswaArrayList.get(position).getNama().toString();
                Toast.makeText(context, nama + " is selected", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return (mahasiswaArrayList != null) ? mahasiswaArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{//ketika kita masukkan card kedalam view ini maka view akan tahu bahwa di card udah ada atribut nama, dll
        private TextView txtNama, txtEmail, txtAlamat, txtNim;
        ImageButton imgFoto;
        //private ImageView imgFoto;
        public ViewHolder(View view){ //implementasi dari recycler view biar bisa dapet layout yg udah dibuat
            super(view);
            txtNim = view.findViewById(R.id.txtNim);
            txtNama = view.findViewById(R.id.txtNimNama);
            txtEmail = view.findViewById(R.id.txtEmailMhs);
            txtAlamat = view.findViewById(R.id.txtAlamatMhs);
            imgFoto = view.findViewById(R.id.imgMhs);
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Mahasiswa");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Mahasiswa");
        }
    }

}
