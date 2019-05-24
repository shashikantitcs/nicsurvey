package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.survey.Model.PendingModel;
import com.example.survey.R;

import java.util.ArrayList;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {

    private ArrayList<PendingModel> pendingModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,house;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            house = (TextView) view.findViewById(R.id.house);

        }
    }

    public PendingAdapter(Context context, ArrayList<PendingModel> pendingModelArrayList) {
        this.mContext = context;
        this.pendingModelArrayList = pendingModelArrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final PendingModel pendingModel = pendingModelArrayList.get(position);

        holder.name.setText(pendingModel.getName());
        holder.house.setText(pendingModel.getHousenumber());





    }

    @Override
    public int getItemCount() {
        return pendingModelArrayList.size();
    }


}

