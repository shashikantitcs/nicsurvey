package com.example.survey.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.survey.Model.PendingModel;
import com.example.survey.Model.RejectedModel;
import com.example.survey.R;

import java.util.ArrayList;

public class RejectedAdapter extends RecyclerView.Adapter<RejectedAdapter.MyViewHolder> {

    private ArrayList<RejectedModel> rejectedModels;
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

    public RejectedAdapter(Context context, ArrayList<RejectedModel> rejectedModelArrayList) {
        this.mContext = context;
        this.rejectedModels = rejectedModelArrayList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rejected_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final RejectedModel rejectedModel = rejectedModels.get(position);

        holder.name.setText(rejectedModel.getName());
        holder.house.setText(rejectedModel.getHousenumber());
        holder.reason.setText(rejectedModel.getReason());




    }

    @Override
    public int getItemCount() {
        return rejectedModels.size();
    }


}
