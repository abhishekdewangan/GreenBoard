package com.structure.greenboard.BaseLib.retrofit;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public interface RetrofitService {

    @FormUrlEncoded
    @POST("{path}")
    Call<ResponseBody> postItems(@Path("path") String partialUrl,@FieldMap Map<String, String> params );
}
