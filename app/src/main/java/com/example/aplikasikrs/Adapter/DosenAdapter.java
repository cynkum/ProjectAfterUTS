package com.example.aplikasikrs.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasikrs.Model.Dosen;
import com.example.aplikasikrs.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {//adapter untuk menampung data
    Context context;
    ArrayList<Dosen> dosenArrayList;//isi data dari luar

    public DosenAdapter(ArrayList<Dosen> dosenArrayList) {
        this.dosenArrayList = dosenArrayList; // set data ke array list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_dosen, parent, false);

        context = parent.getContext();
        return new DosenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtNama.setText(dosenArrayList.get(position).getNama());
        holder.txtGelar.setText(dosenArrayList.get(position).getGelar());
        holder.txtEmail.setText(dosenArrayList.get(position).getEmail());
        holder.txtAlamat.setText(dosenArrayList.get(position).getAlamat());
        holder.imgFoto.getLayoutParams().width=300;
        holder.imgFoto.getLayoutParams().height=300;
        if(dosenArrayList.get(position).getFoto() != null){
            Picasso.with(this.context)
                    .load("https://kpsi.fti.ukdw.ac.id/progmob/"+ dosenArrayList.get(position).getFoto())
                    .into(holder.imgFoto);
        }



        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = dosenArrayList.get(position).getNama().toString();
                Toast.makeText(context, nama + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dosenArrayList != null) ? dosenArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener{//ketika kita masukkan card kedalam view ini maka view akan tahu bahwa di card udah ada atribut nama, dll

            private TextView txtNama, txtGelar, txtEmail, txtAlamat;
            ImageButton imgFoto;
            //private ImageView imgFoto;
        public ViewHolder(View view)
            { //implementasi dari recycler view biar bisa dapet layout yg udah dibuat
                super(view);
                txtNama = view.findViewById(R.id.txtNamaDosen);
                txtGelar = view.findViewById(R.id.txtGelarDosen);
                txtEmail = view.findViewById(R.id.txtEmailDosen);
                txtAlamat = view.findViewById(R.id.txtAlamatDosen);
                imgFoto = view.findViewById(R.id.imgDosen);
                view.setOnCreateContextMenuListener(this);
            }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Pilih Aksi");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Ubah Data Dosen");
            contextMenu.add(this.getAdapterPosition(), view.getId(), 0, "Hapus Data Dosen");
        }
    }

}
