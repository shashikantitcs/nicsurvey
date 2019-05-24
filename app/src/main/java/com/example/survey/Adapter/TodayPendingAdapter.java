package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.survey.Model.TodayPendingModel;
import com.example.survey.R;

import java.util.ArrayList;

public class TodayPendingAdapter extends RecyclerView.Adapter<TodayPendingAdapter.MyViewHolder> {

    private ArrayList<TodayPendingModel> todayPendingModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,house;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            house = (TextView) view.findViewById(R.id.house);

        }
    }

    public TodayPendingAdapter(Context context, ArrayList<TodayPendingModel> todayPendingModels) {
        this.mContext = context;
        this.todayPendingModelArrayList = todayPendingModels;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todaypending_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TodayPendingModel todayPendingModel = todayPendingModelArrayList.get(position);

        holder.name.setText(todayPendingModel.getName());
        holder.house.setText(todayPendingModel.getHousenumber());





    }

    @Override
    public int getItemCount() {
        return todayPendingModelArrayList.size();
    }


}

