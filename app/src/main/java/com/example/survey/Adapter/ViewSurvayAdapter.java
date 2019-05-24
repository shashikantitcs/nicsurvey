package com.example.survey.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.survey.Model.ViewSurvayModel;
import com.example.survey.R;

import java.util.ArrayList;

public class ViewSurvayAdapter extends RecyclerView.Adapter<ViewSurvayAdapter.MyViewHolder> {

    private ArrayList<ViewSurvayModel> viewSurvayModelArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,house;
        LinearLayout cart_plus_minus_layout;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            house = (TextView) view.findViewById(R.id.house);
            cart_plus_minus_layout=(LinearLayout)view.findViewById(R.id.cart_plus_minus_layout);
        }
    }

    public ViewSurvayAdapter(Context context, ArrayList<ViewSurvayModel> viewSurvayModels) {
        this.mContext = context;
        this.viewSurvayModelArrayList = viewSurvayModels;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_survey_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ViewSurvayModel viewSurvayModel = viewSurvayModelArrayList.get(position);

        holder.name.setText(viewSurvayModel.getName());
        holder.house.setText(viewSurvayModel.getHousenumber());
        String statuscheck= viewSurvayModel.getStatus();
       // Toast.makeText(mContext, ""+statuscheck, Toast.LENGTH_SHORT).show();
      /*  if(statuscheck.equals("1"))
        {
            holder.cart_plus_minus_layout.setBackgroundColor(Color.parseColor("#rrggbb"));
        }*/

/*

        if (positionModel.category_background_color.equals("")) {

        } else {
            holder.ll_layout.setBackgroundColor(Color.parseColor("" + positionModel.category_background_color));
        }
        // loading album cover using Glide library
*/





    }

    @Override
    public int getItemCount() {
        return viewSurvayModelArrayList.size();
    }


}

