package com.example.survey.Superviser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.survey.Adapter.User_Adapter;
import com.example.survey.MainActivity;
import com.example.survey.Model.User_Model;
import com.example.survey.R;
import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends AppCompatActivity {
    static User_Adapter user_adapter;
    static ArrayList<User_Model> user_modelArrayList = new ArrayList<>();
    static RecyclerView recycler_view;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("User List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setSubtitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String emp_id=sharedPref.getString("emp_id","");
        String role_id=sharedPref.getString("role_id","");
        survaylist("surveyorCount",role_id);
    }
    public void survaylist(final String choice,final String role_id) {

        if (!Utils.isOnline(this)) {
            Utils.nointernet(this);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().superviserDashboard(choice,role_id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);

                            //  String getstatus=  obj.getString("success");
                            final JSONArray getstatus = obj.optJSONArray("success");
                            user_modelArrayList.clear();
                            for (int i = 0; i < getstatus.length(); i++) {
                                JSONObject jsonObject = getstatus.optJSONObject(i);
                                User_Model user_model = new User_Model();

                                user_model.setName(jsonObject.optString("full_name"));
                                user_model.setEmail(jsonObject.getString("email"));
                                user_model.setMobile(jsonObject.getString("phone"));
                                user_model.setAction(jsonObject.getString("status"));
                                user_model.setId(jsonObject.optString("emp_id"));

                                user_modelArrayList.add(user_model);
                            }
                            user_adapter = new User_Adapter(getApplicationContext(), user_modelArrayList);
                            recycler_view.setAdapter(user_adapter);



                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_LONG).show();
                    if (t.getMessage().equals("connect timed out")) {
                        Utils.nointernet(getApplicationContext());
                    }
                }
            });
        }
    }
    public void userstatus(final String choice,final String emp_id,final String status) {

        if (!Utils.isOnline(this)) {
            Utils.nointernet(this);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().updateuserstatus(choice,emp_id,status);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_LONG).show();
                    if (t.getMessage().equals("connect timed out")) {
                        Utils.nointernet(getApplicationContext());
                    }
                }
            });
        }
    }
}
