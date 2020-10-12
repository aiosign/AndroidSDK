package com.example.showpdf;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdgd.dzpdf.fitz.bean.ParamsBean;
import com.sdgd.dzpdf.fitz.inteface.OnGetSignCallBack;
import com.sdgd.dzpdf.fitz.inteface.OnGetTokenCallBack;
import com.sdgd.dzpdf.fitz.inteface.OnGetTokenListener;
import com.sdgd.dzpdf.fitz.inteface.OnHttpErrorListener;
import com.sdgd.dzpdf.fitz.inteface.OnHttpSuccessListener;
import com.sdgd.dzpdf.fitz.net.primitive_http.HttpUrlTool;
import com.sdgd.dzpdf.fitz.net.primitive_http.OnHttpConnectionListener;
import com.sdgd.dzpdf.fitz.utils.BaseInitPdfSDKUtil;

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
public class PreviewActivity extends AppCompatActivity {



    private static String TAG = "PreviewPdfActivity";

    EditText mUserID;
    EditText mContractId;
    HttpUrlTool mHttpUrlTool;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Button previewPdf = findViewById(R.id.previewPdf);
        mUserID = findViewById(R.id.edUserID);
        mContractId = findViewById(R.id.edContractId);
        BaseInitPdfSDKUtil.getInstance().initSDKUtils(PreviewActivity.this);//初始化
        BaseInitPdfSDKUtil.getInstance().setStatusBarColor(getResources().getColor(R.color.colorBlue));//设置statusBarColor
        BaseInitPdfSDKUtil.getInstance().setTextColor(getResources().getColor(R.color.colorWhite));//设置title字体颜色
        mHttpUrlTool = new HttpUrlTool(this);

        previewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParamsBean bean = new ParamsBean();
                bean.setBrowseType(1);//设置浏览模式 0为签章模式 1为预览模式，2为添加区域印章

//                //设置监听签章成功监听回调
                BaseInitPdfSDKUtil.getInstance().setOnHttpSuccessListener(new OnHttpSuccessListener() {
                    @Override
                    public void onHttpSuccess(String json) {
                        Log.i("zjt", "=签署成功==>" + json);
                    }
                });
                String userId = mUserID.getText().toString();
                if (TextUtils.isEmpty(userId)) {
                    return;
                }
                String contractId = mContractId.getText().toString();
                if (TextUtils.isEmpty(contractId)) {
                    return;
                }
                bean.setUserId(userId);
                bean.setContractId(contractId);


                BaseInitPdfSDKUtil.getInstance().setOnHttpErrorListener(new OnHttpErrorListener() {
                    @Override
                    public void OnHttpError(String error) {
                        Log.i(TAG, error);
                    }
                });

                BaseInitPdfSDKUtil.getInstance().setPDFListener(bean, new OnGetTokenListener() {
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
                                            if (result_code.equals("0")) {
                                                JSONObject object = jsonObject.getJSONObject("data");
                                                String token = object.getString("access_token");
                                                callBack.onSetToken(token);
                                            }


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
//
                    }
                });

                //设置监听签章成功监听回调
                BaseInitPdfSDKUtil.getInstance().setOnHttpSuccessListener(new OnHttpSuccessListener() {
                    @Override
                    public void onHttpSuccess(String json) {


                    }
                });

            }
        });
    }
}
