package com.example.multimatics.myshoppingmall.api.request;

import com.example.multimatics.myshoppingmall.api.BaseApi;
import com.example.multimatics.myshoppingmall.api.response.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Multimatics on 21/07/2016.
 */
public class PostLoginRequest extends BaseApi {
    private onPostLoginRequestListener onPostLoginRequestListener;
    private RequestParams poRequestParams;
    private AsyncHttpClient client;

    public PostLoginRequest(){
        client = getHttpClient();
    }

    public PostLoginRequest.onPostLoginRequestListener getOnPostLoginRequestListener() {
        return onPostLoginRequestListener;
    }

    public void setOnPostLoginRequestListener(PostLoginRequest.onPostLoginRequestListener onPostLoginRequestListener) {
        this.onPostLoginRequestListener = onPostLoginRequestListener;
    }

    public RequestParams getPoRequestParams() {
        return poRequestParams;
    }

    public void setPoRequestParams(RequestParams poRequestParams) {
        this.poRequestParams = poRequestParams;
    }

    @Override
    public void callApi() {
        super.callApi();
        client.post(POST_LOGIN, getPoRequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                User mUser = getUser(response);
                if(mUser != null){
                    if(mUser.getStatus().equals("200")){
                        getOnPostLoginRequestListener().onPostLoginSuccess(mUser);
                    }else{
                        getOnPostLoginRequestListener().onPostLoginFailure(mUser.getMessage());
                    }
                }else {
                    getOnPostLoginRequestListener().onPostLoginFailure("Invalid Response");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                getOnPostLoginRequestListener().onPostLoginFailure("Could not connect to server");
            }
        });
    }

    private User getUser(String response){
        User mUser = null;
        try{
            JSONObject jsonObject = new JSONObject(response);
            mUser = new User();
            if(jsonObject.getString("status").equals("200")){
                JSONObject userObj = jsonObject.optJSONObject("user");

                mUser.setStatus(jsonObject.optString("status"));
                mUser.setEmail(userObj.optString("email"));
                mUser.setUserId(userObj.optString("user_id"));
                mUser.setUsername(userObj.optString("username"));
            }else{
                mUser.setStatus(jsonObject.optString("status"));
                mUser.setMessage(jsonObject.optString("message"));
            }

        }catch (Exception e){
            mUser = null;
        }
        return mUser;
    }

    @Override
    public void cancelRequest() {
        super.cancelRequest();
        if(client != null){
            client.cancelAllRequests(true);
        }
    }

    public interface  onPostLoginRequestListener {
        void onPostLoginSuccess(User user);
        void onPostLoginFailure(String errorMessage);
    }
}
