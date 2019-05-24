package com.example.survey.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.survey.Model.User_Model;
import com.example.survey.R;
import com.example.survey.Superviser.UserList;
import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.MyViewHolder> {

    private ArrayList<User_Model> user_modelArrayList;
    private Context mContext;
    String status;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,mobile,email;
        ImageView action;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            mobile = (TextView) view.findViewById(R.id.mobile);
            email = (TextView) view.findViewById(R.id.email);
            action = (ImageView) view.findViewById(R.id.action);

        }
    }

    public User_Adapter(Context context, ArrayList<User_Model> user_models) {
        this.mContext = context;
        this.user_modelArrayList = user_models;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final User_Model user_model = user_modelArrayList.get(position);
        status = user_model.getAction();
        if(status.equals("1"))
        {
            Glide.with(mContext)
                    .load(R.drawable.checkmark)
                    .override(50,50)
                    .into(holder.action);
        }
        else if (status.equals("0"))
        {
            Glide.with(mContext)
                    .load(R.drawable.closeuser)
                    .override(50,50)
                    .into(holder.action);
        }
        String emailcheck=user_model.getEmail();
        if(emailcheck.equals("null"))
        {
            holder.email.setText("NA");
        }
        else
        {
            holder.email.setText(user_model.getEmail());
        }

        holder.name.setText(user_model.getName());
        holder.mobile.setText(user_model.getMobile());

        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               user_model.getId();

               if(user_model.getAction().equals("1"))
               {
                   userstatus("updateSurveyorStatus", user_model.getId(), "0");
               //  Toast.makeText(mContext, ""+user_model.getAction(), Toast.LENGTH_LONG).show();
                   Glide.with(mContext)
                           .load(R.drawable.closeuser)
                           .override(50,50)
                           .into(holder.action);
                   user_modelArrayList.get(position).setAction("0");
                   notifyItemChanged(position, "prelike");

               }
               else if(user_model.getAction().equals("0")){
                   userstatus("updateSurveyorStatus", user_model.getId(),"1");
                  // Toast.makeText(mContext, ""+user_model.getAction(), Toast.LENGTH_SHORT).show();
                   Glide.with(mContext)
                           .load(R.drawable.checkmark)
                           .override(50,50)
                           .into(holder.action);
                   user_modelArrayList.get(position).setAction("1");
                   notifyItemChanged(position, "prelike");
               }







            }
        });






    }

    @Override
    public int getItemCount() {
        return user_modelArrayList.size();
    }


    public void userstatus(final String choice,final String emp_id,final String status) {

        if (!Utils.isOnline(mContext)) {
            Utils.nointernet(mContext);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().updateuserstatus(choice,emp_id,status);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);
                            String getstatus = obj.getString("status");
                           String success = obj.getString("success");
                            JSONObject success1 = new JSONObject(success);
                            String test = success1.getString("status");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(mContext, ""+e, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(mContext, "fail", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(mContext, ""+t, Toast.LENGTH_LONG).show();
                    if (t.getMessage().equals("connect timed out")) {
                        Utils.nointernet(mContext);
                    }
                }
            });
        }
    }

}

