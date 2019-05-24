package com.example.survey.Superviser;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.survey.R;
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
public class SuperviserDashboard extends Fragment {

    LinearLayout userlist,district,approved,pending,rejected,take_survey;
    Button tekesurvey,addsurvey;
    FragmentTransaction fragmentTransaction;
    TextView usercount;
    SharedPreferences sharedPref;
    public SuperviserDashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_superviser_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dashboard");
        usercount=(TextView)getView().findViewById(R.id.usercount);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String emp_id=sharedPref.getString("emp_id","");
        String role_id=sharedPref.getString("role_id","");
        Toast.makeText(getActivity(), ""+role_id, Toast.LENGTH_SHORT).show();
      /*  tekesurvey=(Button) getView().findViewById(R.id.tekesurvey);
        addsurvey=(Button) getView().findViewById(R.id.addsurvey);
        tekesurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(getActivity(), TakeSurvey.class));
            }
        });

        addsurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSurvey.class));
            }
        });*/

        userlist=(LinearLayout)getView().findViewById(R.id.userlist);
        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserList.class));
            }
        });
        /*
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
               // startActivity(new Intent(getActivity(), SupervicerPending.class));
            }
        });
        rejected=(LinearLayout)getView().findViewById(R.id.rejected);
        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Rejected.class));
            }
        });
        take_survey=(LinearLayout)getView().findViewById(R.id.take_survey);
        take_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TakeSurvay.class));
            }
        });*/
        superdashboard("surveyorCount",role_id);
    }
    public void superdashboard(final String choice,final String role_id) {

        if (!Utils.isOnline(getActivity())) {
            Utils.nointernet(getActivity());
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().superviserDashboard(choice,role_id);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);

                            String getstatus=  obj.getString("status");
                          //  Toast.makeText(getActivity(), ""+getstatus, Toast.LENGTH_SHORT).show();
                            if(getstatus.equals(("true"))) {
                                String numberCount = obj.getString("numberCount");


                                usercount.setText("("+numberCount+")");


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
