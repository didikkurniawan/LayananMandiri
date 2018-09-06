package com.ui.lm.layananmandiri_02;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.BantuanViewHolder> {
    List<Bantuan> bantuans;

    public BantuanAdapter(List<Bantuan> bantuans) {
        this.bantuans = bantuans;
    }
    @Override
    public BantuanViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_bantuan, viewGroup, false);
        BantuanViewHolder bantuanViewHolder = new BantuanViewHolder(v);
        return bantuanViewHolder;
    }
    @Override
    public void onBindViewHolder(BantuanViewHolder bantuanViewHolder, int i) {
        bantuanViewHolder.bantuanNama.setText(bantuans.get(i).getNama());
        bantuanViewHolder.bantuanSdate.setText(bantuans.get(i).getSdate());
        bantuanViewHolder.bantuanEdate.setText(bantuans.get(i).getEdate());
    }
    @Override
    public int getItemCount() {
        return bantuans.size();
    }
    public Bantuan getItem(int position) {
        return bantuans.get(position);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class BantuanViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView bantuanNama;
        TextView bantuanSdate;
        TextView bantuanEdate;
        BantuanViewHolder(View itemView) {
            super(itemView);
            bantuanNama = (TextView) itemView.findViewById(R.id.textViewRowNama);
            bantuanSdate = (TextView) itemView.findViewById(R.id.textViewRowAwal);
            bantuanEdate = (TextView) itemView.findViewById(R.id.textViewRowAkhir);
        }
    }
}
