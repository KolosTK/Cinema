package com.example.cinema.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.example.cinema.Activities.DetailActivity;
import com.example.cinema.Domains.ListFilm;
import com.example.cinema.R;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm items;
    Context context;

    public FilmListAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate =LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(),new RoundedCorners(30));

        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.cover);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",items.getData().get(position).getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }
    public void updateData(ListFilm newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView cover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            cover=itemView.findViewById(R.id.cover);
        }
    }
}
