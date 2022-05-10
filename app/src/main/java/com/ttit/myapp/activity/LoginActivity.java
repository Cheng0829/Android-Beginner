package com.ttit.myapp.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ttit.myapp.R;
import com.ttit.myapp.util.AppConfig;
import com.ttit.myapp.util.StringUtils;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private EditText etAccount, etPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                //getText().toString()输入框获取输入文本转化为字符串,trime去除前后空格
                login(account, pwd);
            }
        });
    }

    private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
            //判断账号是否为空
            showToast("请输入账号");
        }
        if (StringUtils.isEmpty(pwd)) {
            //判断密码是否为空
            showToast("请输入密码");
        }

        //第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Map m = new HashMap();
        m.put("mobile", account);
        m.put("password", pwd);//添加一个集合
        JSONObject jsonObject = new JSONObject(m);//转换成json字符串(看上去像是字典)
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                        , jsonStr);//创建请求体
        //第三步创建Rquest
        Request request = new Request.Builder()
                .url(AppConfig.BASE_URl + "app/login")//请求链接
                .addHeader("contentType", "application/json;charset=UTF-8")//请求头
                .post(requestBodyJson)//请求方法
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            //回调1:失败
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
            }

            @Override
            //回调2:成功
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();//回调内容
                runOnUiThread(new Runnable() {//必须放到主线程中
                    @Override
                    public void run() {
                        showToast(result);
                    }
                });
            }
        });
    }
}