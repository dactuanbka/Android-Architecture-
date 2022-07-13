package com.example.baseproject.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.model.AppInfor;

import java.util.List;

public class ListAppAdapter extends RecyclerView.Adapter<ListAppAdapter.ListAppViewHolder> {
    List<AppInfor> mListAppInfor;

    public ListAppAdapter(List<AppInfor> mListAppInfor) {

        this.mListAppInfor = mListAppInfor;
    }

    @NonNull
    @Override
    public ListAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_app_item,
                parent, false);
        return new ListAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAppViewHolder holder, int position) {
        AppInfor appInfor = mListAppInfor.get(position);
        holder.txtAppName.setText(appInfor.getAppName());
        holder.txtAppDuration.setText(appInfor.getAppDuration());
        holder.imgAppIcon.setImageDrawable(appInfor.getAppIcon());
    }

    @Override
    public int getItemCount() {
        if (mListAppInfor != null) {
            return mListAppInfor.size();
        }
        return 0;
    }
    public class ListAppViewHolder extends RecyclerView.ViewHolder {
        TextView txtAppName, txtAppDuration;
        ImageView imgAppIcon;

        public ListAppViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAppName = itemView.findViewById(R.id.txtAppName);
            txtAppDuration= itemView.findViewById(R.id.txtDuration);
            imgAppIcon= itemView.findViewById(R.id.imgAppIcon);

        }


    }
}

