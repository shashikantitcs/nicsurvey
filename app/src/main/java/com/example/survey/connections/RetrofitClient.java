package com.example.survey.connections;

import com.example.survey.connections.Service;
import com.example.survey.connections.Utils;

import retrofit2.Retrofit;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Service getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.site_url)
                    .build();
        }
        final Service service = retrofit.create(Service.class);
        return service;
    }

}