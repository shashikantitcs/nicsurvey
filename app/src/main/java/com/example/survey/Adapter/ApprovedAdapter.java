package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.survey.Model.ApprovedModel;
import com.example.survey.R;

import java.util.ArrayList;

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.MyViewHolder> {

    private ArrayList<ApprovedModel> approvedModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,house;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            house = (TextView) view.findViewById(R.id.house);

        }
    }

    public ApprovedAdapter(Context context, ArrayList<ApprovedModel> approvedModels) {
        this.mContext = context;
        this.approvedModelArrayList = approvedModels;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.approved_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ApprovedModel approvedModel = approvedModelArrayList.get(position);

        holder.name.setText(approvedModel.getName());
        holder.house.setText(approvedModel.getHousenumber());





    }

    @Override
    public int getItemCount() {
        return approvedModelArrayList.size();
    }


}

