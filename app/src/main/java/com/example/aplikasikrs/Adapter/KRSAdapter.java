package com.example.aplikasikrs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasikrs.Model.KRS;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

public class KRSAdapter extends RecyclerView.Adapter<KRSAdapter.ViewHolder> {//adapter untuk menampung data
    Context context;
    ArrayList<KRS> krsArrayList;//isi data dari luar

    public KRSAdapter(ArrayList<KRS> krsArrayList) {
        this.krsArrayList = krsArrayList; // set data ke array list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_krs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtHari.setText(krsArrayList.get(position).getHari());
        holder.txtSesi.setText(krsArrayList.get(position).getSesi());
        holder.txtJumlahSks.setText(krsArrayList.get(position).getJumlahSks());
        holder.txtDosenPengampu.setText(krsArrayList.get(position).getDosenPengampu());
        holder.txtJmlMhs.setText(krsArrayList.get(position).getJmlMhs());

    }

    @Override
    public int getItemCount() {
        return (krsArrayList != null) ? krsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//ketika kita masukkan card kedalam view ini maka view akan tahu bahwa di card udah ada atribut nama, dll
        private TextView txtHari, txtSesi, txtJumlahSks, txtDosenPengampu, txtJmlMhs;

        public ViewHolder(View view){ //implementasi dari recycler view biar bisa dapet layout yg udah dibuat
            super(view);
            txtHari = view.findViewById(R.id.txtKrsHari);
            txtSesi = view.findViewById(R.id.txtKrsSesi);
            txtJumlahSks = view.findViewById(R.id.txtKrsJmlSks);
            txtDosenPengampu = view.findViewById(R.id.txtKrsDosPengampu);
            txtJmlMhs = view.findViewById(R.id.txtKrsJmlMhs);


        }


    }

}
