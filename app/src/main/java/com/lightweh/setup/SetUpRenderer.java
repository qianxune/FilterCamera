package com.lightweh.setup;


import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.lightweh.facedetection.R;

import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.Renderer;


/**
 * Hello world! 渲染器
 * @author SR1s
 */

public class SetUpRenderer extends Renderer {

    private static final String TAG = "SetUpRenderer";

    private Sphere mEarthSphere;

    public SetUpRenderer(Context context) {
        super(context);
        // 设置渲染帧率
        setFrameRate(60);
    }

    /**
     * 这里进行场景的初始化操作
     */
    @Override
    protected void initScene() {
        // 生成光照
        DirectionalLight light = new DirectionalLight(1f, 0.2f, -1f);
        light.setColor(1f, 1f, 1f);
        light.setPower(2);
        // 把光照添加到场景中
        getCurrentScene().addLight(light);

        // 创建材质
        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColor(0);

        // 纹理素材
        Texture earthTexture = new Texture("Earth", R.drawable.earthtruecolor_nasa_big);
        try {
            // 为材质添加纹理
            material.addTexture(earthTexture);
        } catch (ATexture.TextureException ex) {
            Log.w(TAG, "TEXTURE ERROR", ex);
        }

        // 创建一个球体，即地球
        mEarthSphere = new Sphere(1, 24, 24);
        // 给地球的表面添加材质
        mEarthSphere.setMaterial(material);

        // 把球体加到场景里
        getCurrentScene().addChild(mEarthSphere);

        // 设置摄像头的位置
        getCurrentCamera().setZ(4.2f);
    }

    /**
     * 每一帧渲染的时候调用
     * @param ellapsedRealtime
     * @param deltaTime
     */
    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        mEarthSphere.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
        // 先忽略
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        // 先忽略
    }
}

