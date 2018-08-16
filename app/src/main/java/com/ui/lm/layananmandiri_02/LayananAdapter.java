package com.ui.lm.layananmandiri_02;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.LayananViewHolder> {
    List<Layanan> layanans;

    public LayananAdapter(List<Layanan> layanans) {
        this.layanans = layanans;
    }
    @Override
    public LayananViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layanan, viewGroup, false);
        LayananViewHolder layananViewHolder = new LayananViewHolder(v);
        return layananViewHolder;
    }
    @Override
    public void onBindViewHolder(LayananViewHolder layananViewHolder, int i) {
        layananViewHolder.LayananNomorSurat.setText(layanans.get(i).getNomorSurat());
        layananViewHolder.LayananJenisSurat.setText(layanans.get(i).getJenisSurat());
        layananViewHolder.LayananNamaStaff.setText(layanans.get(i).getNamaStaff());
        layananViewHolder.LayananTanggal.setText(layanans.get(i).getTanggal());
    }
    @Override
    public int getItemCount() {
        return layanans.size();
    }
    public Layanan getItem(int position) {
        return layanans.get(position);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class LayananViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView LayananNomorSurat;
        TextView LayananJenisSurat;
        TextView LayananNamaStaff;
        TextView LayananTanggal;
        LayananViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            LayananNomorSurat = (TextView) itemView.findViewById(R.id.textViewNomorSurat);
            LayananJenisSurat = (TextView) itemView.findViewById(R.id.textViewJenisSurat);
            LayananNamaStaff = (TextView) itemView.findViewById(R.id.textViewNamaStaff);
            LayananTanggal   = (TextView) itemView.findViewById(R.id.textViewTanggalLayanan);
        }
    }
}
