package com.example.aplikasikrs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasikrs.Model.Matkul;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulAdapter.ViewHolder> {//adapter untuk menampung data
    Context context;
    ArrayList<Matkul> matkulArrayList;//isi data dari luar

    public MatkulAdapter(ArrayList<Matkul> matkulArrayList) {
        this.matkulArrayList = matkulArrayList; // set data ke array list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_matkul_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtHari.setText(matkulArrayList.get(position).getHari());
        holder.txtSesi.setText(matkulArrayList.get(position).getSesi());
        holder.txtJumlahSks.setText(matkulArrayList.get(position).getJumlahSks());

    }

    @Override
    public int getItemCount() {
        return (matkulArrayList != null) ? matkulArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//ketika kita masukkan card kedalam view ini maka view akan tahu bahwa di card udah ada atribut nama, dll
        private TextView txtHari, txtSesi, txtJumlahSks;

        public ViewHolder(View view){ //implementasi dari recycler view biar bisa dapet layout yg udah dibuat
            super(view);
            txtHari = view.findViewById(R.id.txtMatkulHari);
            txtSesi = view.findViewById(R.id.txtMatkulSesi);
            txtJumlahSks = view.findViewById(R.id.txtMatkulJmlSks);

        }


    }

}
