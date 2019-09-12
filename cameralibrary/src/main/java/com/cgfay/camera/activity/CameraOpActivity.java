package com.cgfay.camera.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.cgfay.cameralibrary.R;
import com.cgfay.uitls.utils.NotchUtils;
import com.cgfay.uitls.utils.StatusBarUtils;

public class CameraOpActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_op);
        // 判断是否存在刘海屏

    }
}
