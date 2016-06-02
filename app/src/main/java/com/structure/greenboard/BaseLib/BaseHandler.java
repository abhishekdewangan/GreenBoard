package com.structure.greenboard.BaseLib;

import android.util.Log;

import com.google.gson.Gson;
import com.structure.greenboard.BaseLib.retrofit.RetrofitService;
import com.structure.greenboard.BaseLib.retrofit.RetrofitSingleton;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public abstract class BaseHandler {
    private HandlerInf mHandlerInf;
    private RetrofitService mRetrofitService;
    private Gson mGson;
    private OperationResult mOperataionResult;

    public BaseHandler(HandlerInf handlerInf) {
        mHandlerInf = handlerInf;
        mRetrofitService = RetrofitSingleton.getRetrofitSingletonObj().getRetrofitService();
    }

    public abstract void parseRespose(String response);

    public void getItems(String url) {
        final OperationResult operationResult = new OperationResult();
      /*  final Call<ResponseBody> result = mRetrofitService.getItems(url);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    operationResult.setResultStatus(1);
                    operationResult.setResult(data);
                    operationResult.setResultMsg("Successful");
                    getResult(operationResult);
                } catch (IOException e) {
                    e.printStackTrace();
                    operationResult.setResultStatus(0);
                    operationResult.setResultMsg(e.getMessage());
                    getResult(operationResult);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                operationResult.setResultStatus(0);
                operationResult.setResultMsg(t.getMessage());
                getResult(operationResult);
            }
        });*/
    }

    public void postItem(String url, Map<String, String> params) {
        final OperationResult operationResult = new OperationResult();
        final Call<ResponseBody> result = mRetrofitService.postItems(url, params);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    operationResult.setResultStatus(1);
                    operationResult.setResultMsg("Successful");
                    String[] arr = data.split(";");
                    operationResult.setResult(arr[1]);
                    getResult(operationResult);
                } catch (IOException e) {
                    Log.e("Post Error", e.getMessage());
                    operationResult.setResultStatus(0);
                    operationResult.setResultMsg("Fail");
                    operationResult.setResult(null);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

    }

    protected void parseResponse(String response, Class className) {
        if (response != null) {
            try {
                mOperataionResult.setResult(mGson.fromJson(response, className));
                mHandlerInf.onResponseHandle(mOperataionResult);
            } catch (com.google.gson.JsonSyntaxException jsonSyntaxException) {
                Log.e("error ", "--->" + className);
            }
        }
    }

    protected void getResult(OperationResult result) {
        mOperataionResult = result;
        if (result.getResult() != null) {
            mGson = new Gson();
            parseRespose((String) result.getResult());
        }
    }
}
