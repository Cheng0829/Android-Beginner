package com.ttit.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ttit.myapp.activity.BaseActivity;
import com.ttit.myapp.activity.LoginActivity;
import com.ttit.myapp.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin,btnRegister;//设置按钮对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);//找到按钮id
        btnRegister = findViewById(R.id.btn_register);//找到按钮id

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//设置点击事件
//                Intent in = new Intent(MainActivity.this, LoginActivity.class);//跳转
//                startActivity(in);
                navigateTo(LoginActivity.class);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//设置点击事件
//                Intent in = new Intent(MainActivity.this, RegisterActivity.class);//跳转
//                startActivity(in);
                navigateTo(RegisterActivity.class);
            }
        });
    }
}