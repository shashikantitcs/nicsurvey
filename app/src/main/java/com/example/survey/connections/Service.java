package com.example.survey.connections;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {
  @FormUrlEncoded
  @POST("activity.php")
  Call<ResponseBody> logincheck(@Field("choice") String choice,@Field("mobile") String mobile);

  @FormUrlEncoded
  @POST("surveyor.php")
  Call<ResponseBody> dashboardcheck(@Field("choice") String choice, @Field("userId") String userId);

  @FormUrlEncoded
  @POST("surveyor.php")
  Call<ResponseBody> districtcheck(@Field("choice") String choice, @Field("userId") String userId);

  @FormUrlEncoded
  @POST("surveyor.php")
  Call<ResponseBody> getallsurvey(@Field("choice") String choice, @Field("userId") String userId, @Field("type") String type);



  @FormUrlEncoded
  @POST("surveyor.php")
  Call<ResponseBody> sendarray(@Field("choice") String choice,
                               @Field("user_id") String user_id,
                               @Field("Cluster") String Cluster,
                               @Field("cluster_category") String cluster_category,
                               @Field("location") String location,
                               @Field("subdistrict") String subdistrict,
                               @Field("head_of_houshold") String head_of_houshold,
                               @Field("house_number") String house_number,
                               @Field("handicraft_practiced") String handicraft_practiced,
                               @Field("family_cccupation") String family_cccupation,
                               @Field("practiced_houshold") String practiced_houshold,
                               @Field("seasonality_Pattern") String seasonality_Pattern,
                               @Field("religion") String religion,
                               @Field("social_group") String social_group,
                               @Field("economic_group") String economic_group,
                               @Field("house_type") String house_type,
                               @Field("house_owned") String house_owned,
                               @Field("drinking_water") String drinking_water,
                               @Field("drinking_water_source") String drinking_water_source,
                               @Field("electric_connection") String electric_connection,
                               @Field("telephone_conn") String telephone_conn,
                               @Field("ownership_of_assets") String ownership_of_assets,
                               @Field("income_of_hhs") String income_of_hhs);


  @FormUrlEncoded
  @POST("supervisor.php")
  Call<ResponseBody> superviserDashboard(@Field("choice") String choice, @Field("role_id") String role_id);


  @FormUrlEncoded
  @POST("supervisor.php")
  Call<ResponseBody> updateuserstatus(@Field("choice") String choice, @Field("emp_id") String emp_id,@Field("status") String status);
}

