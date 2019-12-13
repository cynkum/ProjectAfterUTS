package com.example.aplikasikrs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasikrs.Model.Kelas;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {
    Context context;
    ArrayList<Kelas> kelasArrayList;//isi data dari luar

    public KelasAdapter(ArrayList<Kelas> kelasArrayList) {
        this.kelasArrayList = kelasArrayList; // set data ke array list
    }

    @NonNull
    @Override
    public KelasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtKode.setText(kelasArrayList.get(position).getKode());
        holder.txtHari.setText(kelasArrayList.get(position).getHari());
        holder.txtSesi.setText(kelasArrayList.get(position).getSesi());
        holder.txtDosen.setText(kelasArrayList.get(position).getDosPengampu());
        holder.txtJmlMhs.setText(kelasArrayList.get(position).getJmlMhs());
    }

    @Override
    public int getItemCount() {
        return (kelasArrayList != null) ? kelasArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//ketika kita masukkan card kedalam view ini maka view akan tahu bahwa di card udah ada atribut nama, dll
        private TextView txtKode, txtHari, txtSesi, txtDosen, txtJmlMhs;

        public ViewHolder(View view){ //implementasi dari recycler view biar bisa dapet layout yg udah dibuat
            super(view);
            txtKode = view.findViewById(R.id.txtKelasKode);
            txtHari = view.findViewById(R.id.txtHariKode);
            txtSesi = view.findViewById(R.id.txtSesiKelas);
            txtDosen = view.findViewById(R.id.txtDosenKelas);
            txtJmlMhs = view.findViewById(R.id.txtJmlKelas);

        }


    }

}

