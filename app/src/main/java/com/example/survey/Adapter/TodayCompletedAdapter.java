package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.survey.Model.TodayCompletedModel;
import com.example.survey.R;

import java.util.ArrayList;

public class TodayCompletedAdapter extends RecyclerView.Adapter<TodayCompletedAdapter.MyViewHolder> {

    private ArrayList<TodayCompletedModel> todayCompletedModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,house,reason;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            house = (TextView) view.findViewById(R.id.house);
            reason = (TextView) view.findViewById(R.id.reason);

        }
    }

    public TodayCompletedAdapter(Context context, ArrayList<TodayCompletedModel> todayCompletedModelArrayList) {
        this.mContext = context;
        this.todayCompletedModelArrayList = todayCompletedModelArrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todaycompleted_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TodayCompletedModel todayCompletedModel = todayCompletedModelArrayList.get(position);

        holder.name.setText(todayCompletedModel.getName());
        holder.house.setText(todayCompletedModel.getHousenumber());





    }

    @Override
    public int getItemCount() {
        return todayCompletedModelArrayList.size();
    }


}
