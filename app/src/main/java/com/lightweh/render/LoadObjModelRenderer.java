package com.lightweh.render;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.lightweh.facedetection.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.Renderer;


/**
 * 加载模型渲染器
 * @author SR1s
 */

public class LoadObjModelRenderer extends Renderer {

    private static final String TAG = "LoadObjModelRenderer";

    private Object3D mModel;

    public LoadObjModelRenderer(Context context) {
        super(context);
        // 设置渲染帧率
        setFrameRate(60);
    }

    @Override
    protected void initScene() {
        // 生成光照
        DirectionalLight light = new DirectionalLight(1f, 0.2f, -1f);
        light.setColor(1f, 1f, 1f);
        light.setPower(2);
        // 把光照添加到场景中
        getCurrentScene().addLight(light);
        // 设置场景的背景为白色
        getCurrentScene().setBackgroundColor(0xffffffff);

        // 使用LoderOBJ 来加载OBJ 模型文件
        LoaderOBJ loader = new LoaderOBJ(this, R.raw.bb8);
        try {
            // 解析模型文件
            loader.parse();

            // 获取模型对象
            mModel = loader.getParsedObject();

            // 把模型添加到场景中去
            getCurrentScene().addChild(mModel);

            // 缩放模型，不然模型就太大了
            mModel.setScale(.01f);

            // 设置摄像头的位置
            getCurrentCamera().setZ(4.2f);

        } catch (Exception ex) {
            Log.w(TAG, "LoaderOBJ ERROR", ex);
        }

    }

    /**
     * 每一帧渲染的时候调用
     * @param ellapsedRealtime
     * @param deltaTime
     */
    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        // 每一帧都绕着Y轴旋转
        mModel.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
        //ignore
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        // ignore
    }
}
