package com.example.survey.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.survey.Approved;
import com.example.survey.District;
import com.example.survey.Pending;
import com.example.survey.R;
import com.example.survey.Rejected;
import com.example.survey.TakeSurvay;
import com.example.survey.TodayCompleted;
import com.example.survey.TodayPending;
import com.example.survey.ViewSurvey;
import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {
 TextView totalsurvey,totaldistrict,approvedsurvey,pendingsurvey,rejectedsurveytotal,todaycompletedsurvey,todaypendingsurvey;
LinearLayout first,district,approved,pending,rejected,take_survey,completed,today_pending;
    SharedPreferences sharedPref;
    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            totalsurvey=(TextView)getView().findViewById(R.id.totalsurvey) ;
            totaldistrict=(TextView)getView().findViewById(R.id.totaldistrict) ;
            approvedsurvey=(TextView)getView().findViewById(R.id.approvedsurvey) ;
            pendingsurvey=(TextView)getView().findViewById(R.id.pendingsurvey) ;
            rejectedsurveytotal=(TextView)getView().findViewById(R.id.rejectedsurveytotal) ;
            todaycompletedsurvey=(TextView)getView().findViewById(R.id.todaycompletedsurvey) ;
            todaypendingsurvey=(TextView)getView().findViewById(R.id.todaypendingsurvey) ;
        first=(LinearLayout)getView().findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewSurvey.class));
            }
        });
        district=(LinearLayout)getView().findViewById(R.id.district);
        district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), District.class));
            }
        });
        approved=(LinearLayout)getView().findViewById(R.id.approved);
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Approved.class));
            }
        });
        pending=(LinearLayout)getView().findViewById(R.id.pending);
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pending.class));
            }
        });
        rejected=(LinearLayout)getView().findViewById(R.id.rejected);
        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Rejected.class));
            }
        });
        completed=(LinearLayout)getView().findViewById(R.id.completed);
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TodayCompleted.class));
            }
        });
        today_pending=(LinearLayout)getView().findViewById(R.id.today_pending);
        today_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TodayPending.class));
            }
        });
        take_survey=(LinearLayout)getView().findViewById(R.id.take_survey);
        take_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TakeSurvay.class));
            }
        });
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String emp_id=sharedPref.getString("emp_id","");
        String role_id=sharedPref.getString("role_id","");

        dashboard("dashboardData",emp_id);
    }
    public void dashboard(final String choice,final String userId) {

        if (!Utils.isOnline(getActivity())) {
            Utils.nointernet(getActivity());
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().dashboardcheck(choice,userId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);

                            String getstatus=  obj.getString("status");
                            if(getstatus.equals(("true"))) {
                                String success = obj.getString("success");
                                JSONObject subObject = obj.getJSONObject("success");
                                String totalSurvey = subObject.getString("allTotalSurvey");
                                totalsurvey.setText("("+totalSurvey+")");

                                String totalDistCovered = subObject.getString("totalDistCovered");
                                totaldistrict.setText("("+totalDistCovered+")");

                                String pendingSurvey = subObject.getString("pendingSurvey");
                                pendingsurvey.setText("("+pendingSurvey+")");


                                String approvedSurvey = subObject.getString("approvedSurvey");
                                approvedsurvey.setText("("+approvedSurvey+")");

                                String rejectedSurvey = subObject.getString("rejectedSurvey");
                                rejectedsurveytotal.setText("("+rejectedSurvey+")");

                                String todayCovered = subObject.getString("todayCovered");
                                todaycompletedsurvey.setText("("+todayCovered+")");

                                String todayPending = subObject.getString("todayPending");
                                todaypendingsurvey.setText("("+todayPending+")");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
                    if (t.getMessage().equals("connect timed out")) {
                        Utils.nointernet(getActivity());
                    }
                }
            });
        }
    }
}
