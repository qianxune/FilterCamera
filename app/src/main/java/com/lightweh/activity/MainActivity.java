package com.lightweh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lightweh.render.LoadObjModelRenderer;

import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建一个用于展示的SurfaceView，注意这个SurfaceView的包名是org.rajawali3d.view
        final SurfaceView surface = new SurfaceView(this);
        // 渲染帧率
        surface.setFrameRate(60);
        // 渲染模式：仅在需要更新界面的时候渲染
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);

        // 把用于显示的SurfaceView放到界面上
        setContentView(surface);

        // 创建渲染器
        Renderer renderer = new LoadObjModelRenderer(this);
        // 把渲染器设置给Surface，后续Surface就会使用它来绘制界面
        surface.setSurfaceRenderer(renderer);
    }
}
