#  Android framework 引用说明
## 1、pdf_use_1.0.2.arr引用配置
### 1.1 添加权限
```
   <uses-permission android:name="android.permission.RECEIVE_USER_PRESENT" />
   <uses-permission android:name="android.permission.INTERNET" />
   <uses-permission android:name="android.permission.WAKE_LOCK" />
   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
   <uses-permission android:name="android.permission.VIBRATE" />
   <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
   <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
   <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
   <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
   <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
   <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
```
### 1.2 复制aar到lib中
### 1.3 在build.gralde引入下列代码
```
 implementation(name:'pdf_use_1.0.2',ext:"aar");
 implementation 'androidx.cardview:cardview:1.0.0'
 implementation 'androidx.recyclerview:recyclerview:1.1.0'
```
## 2、 签章功能调用
### 2.1 初始化SDk
```
BaseInitPdfSDKUtil.getInstance().initSDKUtils(MainActivity.this);//初始化
BaseInitPdfSDKUtil.getInstance().setStatusBarColor(getResources().getColor(R.color.colorBlue));//设置statusBarColor
BaseInitPdfSDKUtil.getInstance().setTextColor(getResources().getColor(R.color.colorWhite));//设置title字体颜色
```
### 2.2 设置回调监听
```
    //失败回调
    BaseInitPdfSDKUtil.getInstance().setOnHttpErrorListener(new OnHttpErrorListener() {
                    @Override
                    public void OnHttpError(String error) {
                        Log.i(TAG, error);
                    }
                });

      //设置监听签章成功监听回调
       BaseInitPdfSDKUtil.getInstance().setOnHttpSuccessListener(new OnHttpSuccessListener() {
            @Override
              public void onHttpSuccess(String json) {


                    }
                });
                 
```
## 3、开始签章

### 3.1  设置传递的参数
```
 ParamsBean bean = new ParamsBean();
  bean.setBrowseType(0);//设置浏览模式 0为签章模式 1为预览模式，2为添加区域印章
 bean.setUserId(“自己id”);
  bean.setContractId(“合同id”);
  
```
### 3.2 使用区域签名
```
    ArrayList<AreaInfo> list = new ArrayList<>();
    AreaInfo info = new AreaInfo();
    info.setLocationX(“输入的X坐标”);
    info.setLocationY(“输入的Y坐标”);
    info.setPageIndex(“签名区域所在页”);
   list.add(info);
    bean.setData(list);

```
###   3.3 设置签章信息
```
   BaseInitPdfSDKUtil.getInstance().setPDFListener(bean, new OnGetTokenListener() {
                    @Override
                    public void onGetToken(Map<String, String> map, OnGetTokenCallBack onGetTokenCallBack) {
                        //处理自己逻辑获取 token ,并调用onGetTokenCallBack回传token
                        onGetTokenCallBack.onSetToken("获取到的token");
                    }

                    @Override
                    public void onGetSign(Map<String, Object> map, String s, OnGetSignCallBack onGetSignCallBack) {
                        //处理自己逻辑获取 sign ,onGetSignCallBack
                        onGetSignCallBack.onSetSign("获取到的sign");
                    }
                });

```
## 4、人脸识别
### 4.1 初始化SDK
```
    BaseInitFaceDiscernSDKUtil.getInstance().initSDKUtils(FaceActivity.this);//初始化
    BaseInitFaceDiscernSDKUtil.getInstance().setStatusBarColor(getResources().getColor(R.color.colorBlue));//设置statusBarColor
    BaseInitFaceDiscernSDKUtil.getInstance().setTextColor(getResources().getColor(R.color.colorWhite));//设置title字体颜色
```
### 4.1  设置调用回调
```
 //失败回调
    BaseInitFaceDiscernSDKUtil.getInstance().setOnHttpErrorListener(new OnHttpErrorListener() {
                    @Override
                    public void OnHttpError(String error) {
                        Log.i(TAG, error);
                    }
                });

      //设置监听签章成功监听回调
       BaseInitFaceDiscernSDKUtil.getInstance().setOnHttpSuccessListener(new OnHttpSuccessListener() {
            @Override
              public void onHttpSuccess(String json) {


                    }
                });
```

### 4.3 设置信息
```
BaseInitFaceDiscernSDKUtil.getInstance().setFaceDiscernListener("", new OnGetTokenListener() {
                    @Override
                    public void onGetToken(Map<String, String> map, OnGetTokenCallBack onGetTokenCallBack) {
                        //处理自己逻辑获取 token ,并调用onGetTokenCallBack回传token
                        onGetTokenCallBack.onSetToken("获取到的token");
                    }

                    @Override
                    public void onGetSign(Map<String, Object> map, String s, OnGetSignCallBack onGetSignCallBack) {
                        //处理自己逻辑获取 sign ,onGetSignCallBack
                        onGetSignCallBack.onSetSign("获取到的sign");
                    }
                });
```

### 4.4 设置参数
```

  FaceParamsBean bean = new FaceParamsBean();
  bean.setCardNum(edCardNum.getText().toString());
  bean.setName(edName.getText().toString());
  bean.setEditable(true);//true可编辑，false不可编辑
```
### 4.5 调用人脸识别
```
             startActivity(new Intent(FaceActivity.this, RealNameServeTwoActivity.class)
                      .putExtra(CommonUtils.FACE_NEED_MESSAGE,bean));
```



#### 增加传入baseUrl方法，实现平台url，三方外部传入






