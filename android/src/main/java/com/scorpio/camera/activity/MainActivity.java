package com.scorpio.camera.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.cgfay.camera.engine.PreviewEngine;
import com.cgfay.camera.engine.model.AspectRatio;
import com.cgfay.camera.engine.model.GalleryType;
import com.cgfay.camera.listener.OnGallerySelectedListener;
import com.cgfay.camera.listener.OnPreviewCaptureListener;
import com.cgfay.filter.glfilter.resource.FilterHelper;
import com.cgfay.filter.glfilter.resource.MakeupHelper;
import com.cgfay.filter.glfilter.resource.ResourceHelper;
import com.cgfay.image.activity.ImageEditActivity;
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
        initResources();
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
                previewCamera();
                break;
            case R.id.iv_gallery:
                ARouterUtils.navigation(ARouterConstant.ACTIVITY_LOVE_ACTIVITY);
                Log.d("scorpiozjm","testActivity");
                break;
        }
    }

    /**
     * 打开预览页面
     */
    private void previewCamera() {
        PreviewEngine.from(this)
                .setCameraRatio(AspectRatio.Ratio_16_9)
                .showFacePoints(false)
                .showFps(true)
                .backCamera(true)
                .setGalleryListener(new OnGallerySelectedListener() {
                    @Override
                    public void onGalleryClickListener(GalleryType type) {
                        //scanMedia(type == GalleryType.ALL);
                    }
                }).setPreviewCaptureListener(new OnPreviewCaptureListener() {
            @Override
            public void onMediaSelectedListener(String path, GalleryType type) {
                if (type == GalleryType.PICTURE) {
                    Intent intent = new Intent(MainActivity.this, ImageEditActivity.class);
                    intent.putExtra(ImageEditActivity.IMAGE_PATH, path);
                    intent.putExtra(ImageEditActivity.DELETE_INPUT_FILE, true);
                    startActivity(intent);
                }
            }
        }).startPreview();
    }

    private void initResources() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ResourceHelper.initAssetsResource(MainActivity.this);
                FilterHelper.initAssetsFilter(MainActivity.this);
                MakeupHelper.initAssetsMakeup(MainActivity.this);
            }
        }).start();
    }

}
