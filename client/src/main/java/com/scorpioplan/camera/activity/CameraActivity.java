package com.scorpioplan.camera.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.scorpioplan.camera.R;


@Route(path = "/camera/CameraActivity")

public class CameraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
        initView();
    }
    public void initView() {
       // ARouterUtils.injectActivity(this);

    }




}
