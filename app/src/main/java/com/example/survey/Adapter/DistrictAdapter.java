package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.survey.Model.DistrictModel;
import com.example.survey.R;

import java.util.ArrayList;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.MyViewHolder> {

    private ArrayList<DistrictModel> districtModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,count;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            count = (TextView) view.findViewById(R.id.count);

        }
    }

    public DistrictAdapter(Context context, ArrayList<DistrictModel> districtModelArrayList) {
        this.mContext = context;
        this.districtModelArrayList = districtModelArrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.district_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final DistrictModel districtModel = districtModelArrayList.get(position);

        holder.name.setText(districtModel.getName());
        holder.count.setText("("+districtModel.getCount()+")");





    }

    @Override
    public int getItemCount() {
        return districtModelArrayList.size();
    }


}

