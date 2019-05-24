package com.example.survey;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

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

public class Testing extends AppCompatActivity {
    String[] district = { "Select District","Anantnag", "Badgam", "Bandipora", "Baramulla", "Doda","Ganderbal","Jammu","Kargil","Kathua","Kishtwar","Kulgam","Kupwara","Leh",
            "Poonch","Pulwama","Rajouri","Ramban","Reasi","Samba","Shopian","Srinagar","Udhampur"};
    AutoCompleteTextView userlist;
    String selecteddistrict;
    List<String> listist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,listist);
        //Getting the instance of AutoCompleteTextView
        userlist = (AutoCompleteTextView) findViewById(R.id.dis_spinnerspinner);
        userlist.setThreshold(1);//will start working from first character
        userlist.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        userlist.setTextColor(Color.BLACK);
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selecteddistrict = (String) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), ""+selecteddistrict, Toast.LENGTH_SHORT).show();
            }
        });
        survaylist("surveyorCount","19");
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

                            for (int i = 0; i < getstatus.length(); i++) {
                                JSONObject jsonObject = getstatus.optJSONObject(i);
                                listist.add("" + jsonObject.getString("full_name"));
                               // cList.add(jsonObject.optString("full_name"));

                              jsonObject.getString("email");
                              jsonObject.getString("phone");
                               jsonObject.getString("status");
                              jsonObject.optString("emp_id");


                            }




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
