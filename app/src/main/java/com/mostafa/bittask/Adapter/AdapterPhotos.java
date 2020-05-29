package com.mostafa.bittask.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafa.bittask.Model.Photos.DataItem;
import com.mostafa.bittask.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewHolder> {

    List<DataItem> dataItems;

    public AdapterPhotos(List<DataItem> dataItems) {
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final DataItem dataItem = dataItems.get(position);
        Picasso.get()
                .load(dataItem.getImage())
                .placeholder(R.drawable.user_defult)
                .error(android.R.drawable.stat_notify_error)
                .into(viewHolder.photosItems);

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, dataItem);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (dataItems == null)
            return 0;
        return dataItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photosItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photosItems = itemView.findViewById(R.id.id_iv_photo);

        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, DataItem dataItem);
    }

}