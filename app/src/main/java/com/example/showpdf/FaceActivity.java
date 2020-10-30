package com.example.showpdf;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdgd.dzpdf.fitz.bean.FaceParamsBean;
import com.sdgd.dzpdf.fitz.inteface.CommonUtils;
import com.sdgd.dzpdf.fitz.inteface.OnGetSignCallBack;
import com.sdgd.dzpdf.fitz.inteface.OnGetTokenCallBack;
import com.sdgd.dzpdf.fitz.inteface.OnGetTokenListener;
import com.sdgd.dzpdf.fitz.net.primitive_http.HttpUrlTool;
import com.sdgd.dzpdf.fitz.net.primitive_http.OnHttpConnectionListener;
import com.sdgd.dzpdf.fitz.ui.face_recognition.RealNameServeTwoActivity;
import com.sdgd.dzpdf.fitz.utils.BaseInitFaceDiscernSDKUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：zjt on 2020/10/11.
 * 邮箱：13120490031@163.com
 * 版本：v1.0
 * qq：1158710428
 */
public class FaceActivity extends AppCompatActivity {

    private static final String TAG ="FaceActivity";

    HttpUrlTool mHttpUrlTool;
    EditText edName,edCardNum,editable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        Button face = findViewById(R.id.starFace);


        mHttpUrlTool = new HttpUrlTool(this);



        BaseInitFaceDiscernSDKUtil.getInstance().initSDKUtils(FaceActivity.this);//初始化
        BaseInitFaceDiscernSDKUtil.getInstance().setStatusBarColor(getResources().getColor(R.color.colorBlue));//设置statusBarColor
        BaseInitFaceDiscernSDKUtil.getInstance().setTextColor(getResources().getColor(R.color.colorWhite));//设置title字体颜色
        BaseInitFaceDiscernSDKUtil.getInstance().setBaseUrl("https://open.aiosign.com/api/");
        BaseInitFaceDiscernSDKUtil.getInstance().setFaceDiscernListener("00755149673060782080", new OnGetTokenListener() {
            @Override
            public void onGetToken(Map<String, String> parameters, final OnGetTokenCallBack callBack) {
                AsyncTask task = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        mHttpUrlTool.requestPost("http://119.163.197.219:9000/paas_app_server/getToken", headers, null, new OnHttpConnectionListener() {
                            @Override
                            public void onHttpConnectionSuccess(String response) {
                                Log.i(TAG, response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result_code = jsonObject.getString("result_code");
                                    if (!result_code.equals("0")) {

                                    }

                                    JSONObject object = jsonObject.getJSONObject("data");
                                    String token = object.getString("access_token");
                                    callBack.onSetToken(token);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onHttpConnectionError(String error) {
                                Log.i(TAG, error);

                            }
                        });
                        return null;
                    }
                }.execute("");
            }

            @Override
            public void onGetSign(final Map<String, Object> requestBody, String token, final OnGetSignCallBack callBack) {
                final Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json");
                header.put("Authentication", token);
//                        mHttpUrlTool.setConnection(header,"POST");

                AsyncTask task = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        mHttpUrlTool.requestPost("http://119.163.197.219:9000/paas_app_server/getSign", header, requestBody, new OnHttpConnectionListener() {
                            @Override
                            public void onHttpConnectionSuccess(String response) {
                                Log.i(TAG, response.toString());
                                try {
                                    JSONObject signJSONObject = new JSONObject(response);
//                                            String code = jsonObject.getString("result_code");
                                    JSONObject signObject = signJSONObject.getJSONObject("data");
                                    String sign = signObject.getString("sign");
                                    callBack.onSetSign(sign);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onHttpConnectionError(String error) {

                            }
                        });
                        return null;
                    }
                }.execute("");
            }
        });




        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FaceParamsBean bean = new FaceParamsBean();
                bean.setCardNum(edCardNum.getText().toString());
                bean.setName(edName.getText().toString());
                if(TextUtils.isEmpty(editable.getText().toString())){
                    bean.setEditable(true);
                }else{
                    if(editable.getText().toString().equals("1")){//1不可编辑，0可编辑
                        bean.setEditable(false);
                    }else{
                        bean.setEditable(true);
                    }
                }

                startActivity(new Intent(FaceActivity.this, RealNameServeTwoActivity.class)
                        .putExtra(CommonUtils.FACE_NEED_MESSAGE,bean));

            }
        });
    }
}
