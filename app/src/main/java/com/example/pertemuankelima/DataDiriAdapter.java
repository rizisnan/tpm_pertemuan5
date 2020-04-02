package com.example.pertemuankelima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuankelima.Database.DataDiri;

import java.util.List;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {

    private DataDiriListerner listerner;
    private List<DataDiri> dataDiris;

    public Context context;
    public String nama,alamat;
    public int id;
    public char jk;

    public DataDiriAdapter(Context context, DataDiriListerner listerner) {
        this.context = context;
        this.listerner = listerner;
    }

    public List<DataDiri> getDataDiris(){
        return dataDiris;
    }

    public void setDataDiris(List<DataDiri> dataDiris){
        this.dataDiris = dataDiris;
    }


    @NonNull
    @Override
    public DataDiriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_diri_item, parent, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull DataDiriAdapter.ViewHolder holder,final int position) {
        holder.tvNama.setText(getDataDiris().get(position).getNama());
        holder.tvAlamat.setText(getDataDiris().get(position).getAlamat());
        holder.tvGender.setText(""+getDataDiris().get(position).getJk());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listerner.onButtonDelete(getDataDiris().get(position));
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=getDataDiris().get(position).getId();
                nama=getDataDiris().get(position).getNama();
                alamat=getDataDiris().get(position).getAlamat();
                jk=getDataDiris().get(position).getJk();

                Intent intent= new Intent(v.getContext(),UpdateActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("nama",nama);
                intent.putExtra("alamat",alamat);
                intent.putExtra("jk",jk);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return getDataDiris().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvAlamat, tvGender;
        private Button btnDelete,btnUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvGender = itemView.findViewById(R.id.tvKelamin);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
