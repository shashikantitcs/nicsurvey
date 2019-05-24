package com.example.survey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.survey.Adapter.DistrictAdapter;
import com.example.survey.Model.DistrictModel;
import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class District extends AppCompatActivity {
    private DistrictAdapter districtAdapter;
    private ArrayList<DistrictModel> districtModelArrayList = new ArrayList<>();
    public RecyclerView recycler_view;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.district);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Disctrict Covered");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setSubtitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        survaylist("getCoveredDistrict",emp_id,"");
    }

    public void survaylist(final String choice,final String userId,String type) {

        if (!Utils.isOnline(this)) {
            Utils.nointernet(this);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().getallsurvey(choice,userId,type);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);

                            //  String getstatus=  obj.getString("success");
                            final JSONArray getstatus = obj.optJSONArray("success");
                            districtModelArrayList.clear();
                            for (int i = 0; i < getstatus.length(); i++) {
                                JSONObject jsonObject = getstatus.optJSONObject(i);
                                DistrictModel districtModel = new DistrictModel();

                                districtModel.setName(jsonObject.optString("name"));
                                districtModel.setCount(jsonObject.getString("cover_distict"));


                                districtModelArrayList.add(districtModel);
                            }
                            districtAdapter = new DistrictAdapter(getApplicationContext(), districtModelArrayList);
                            recycler_view.setAdapter(districtAdapter);



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
