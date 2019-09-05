package com.scorpio.camera.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scorpio.camera.R;
import com.scorpio.library.arounter.ARouterConstant;
import com.scorpio.library.arounter.ARouterUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView IvCamera;
    private ImageView IvGallery;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouterUtils.injectActivity(this);
        initView();
    }
    private void initView(){
        IvCamera=findViewById(R.id.iv_camera);
        IvGallery=findViewById(R.id.iv_gallery);
        IvCamera.setOnClickListener(this);
        IvGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_camera:
                //RouterUtils.navigation(ARouterConstant.ACTIVITY_CAMERA_ACTIVITY);
                ARouter.getInstance().build(ARouterConstant.ACTIVITY_CAMERA_ACTIVITY).navigation();
                 Log.d("scorpiozjm","cameraActivity");
        /*        Intent intent=new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);*/

                break;
            case R.id.iv_gallery:
                ARouterUtils.navigation(ARouterConstant.ACTIVITY_TEST_ACTIVITY);
                Log.d("scorpiozjm","testActivity");
                break;
        }
    }
}
