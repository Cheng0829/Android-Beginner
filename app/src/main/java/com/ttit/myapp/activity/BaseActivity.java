package com.ttit.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.ttit.myapp.MainActivity;

public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    //此类专门用来专门显示提醒消息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
    public void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
    public void navigateTo(Class cls){
        Intent in = new Intent(mContext, cls);//跳转
        startActivity(in);
    }
}
