package com.scorpio.camera.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cgfay.camera.engine.PreviewEngine;
import com.cgfay.camera.engine.model.AspectRatio;
import com.cgfay.camera.engine.model.GalleryType;
import com.cgfay.camera.listener.OnGallerySelectedListener;
import com.cgfay.camera.listener.OnPreviewCaptureListener;
import com.cgfay.filter.glfilter.resource.FilterHelper;
import com.cgfay.filter.glfilter.resource.MakeupHelper;
import com.cgfay.filter.glfilter.resource.ResourceHelper;
import com.cgfay.image.activity.ImageEditActivity;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.scorpio.camera.R;
import com.scorpio.library.arounter.ARouterUtils;
import com.seu.magiccamera.activity.ImageActivity;
import com.seu.magicfilter.display.MagicImageDisplay;
import com.seu.magicfilter.filter.helper.MagicFilterType;
import com.seu.magicfilter.utils.MagicSDK;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnCamera;
    private Button mBtnGallery;

    private MagicImageDisplay mMagicImageDisplay;

    private GLSurfaceView mGlsurfaceview_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouterUtils.injectActivity(this);
        initView();
        initResources();
        permission();

        MagicSDK.getInstance();
    }
    private void initView(){
        mGlsurfaceview_image=findViewById(R.id.glsurfaceview_image);
        mBtnCamera=findViewById(R.id.btn_camera);
        mBtnGallery=findViewById(R.id.btn_gallery);
        mBtnCamera.setOnClickListener(this);
        mBtnGallery.setOnClickListener(this);
        mMagicImageDisplay = new MagicImageDisplay(this,mGlsurfaceview_image);

        Bitmap bitmap=getImageFromAssetsFile(this,"bg_main.png");
        mMagicImageDisplay.setImageBitmap(bitmap);



    }
    public void permission() {
        PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                //Toast.makeText(MySpaceActivity.this, "访问存储权限", Toast.LENGTH_LONG).show();

            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                Toast.makeText(MainActivity.this, "存储权限不可以，无法上传头像", Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA);
    }

    public static Bitmap getImageFromAssetsFile(Context mContext, String fileName)
    {
        Bitmap image = null;
        AssetManager am = mContext.getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_camera:
                mMagicImageDisplay.setFilter(MagicFilterType.ROMANCE);
                //RouterUtils.navigation(ARouterConstant.ACTIVITY_CAMERA_ACTIVITY);
               // previewCamera();
                break;
            case R.id.btn_gallery:
                Intent intent=new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
                //ARouterUtils.navigation(ARouterConstant.ACTIVITY_LOVE_ACTIVITY);
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
