package com.scorpio.camera.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scorpio.camera.R;
import com.scorpio.library.arounter.ARouterConstant;


@Route(path = ARouterConstant.ACTIVITY_TEST_ACTIVITY)
public class TestActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //ARouterUtils.injectActivity(this);

    }



}

