package com.ui.lm.layananmandiri_02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ui.lm.layananmandiri_02.model.Artikel;
import com.ui.lm.layananmandiri_02.R;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Artikel> mData;
    RequestOptions options;

    public RecyclerViewAdapter(Context mContext, List<Artikel> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.artikel_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.judul.setText(mData.get(position).getJudul());
        holder.kategori.setText(mData.get(position).getKategori());
//        load image
        Glide.with(mContext).load(mData.get(position).getUrl()).apply(options).into(holder.url);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView judul;
        TextView kategori;
        ImageView url;

        public MyViewHolder(View itemView){
             super(itemView);

             judul = itemView.findViewById(R.id.judul);
             kategori = itemView.findViewById(R.id.kategori);
             url = itemView.findViewById(R.id.thumnail);
         }
     }
}
