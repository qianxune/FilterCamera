package com.cgfay.landmark;

import android.graphics.Point;


import java.util.ArrayList;

public class DlibLandMark {
    public static OneFace transPoint(ArrayList<Point> results, int height, int width){

        OneFace oneFace = new OneFace();
        oneFace.vertexPoints = new float[results.size()*2];
        for (int i = 0; i < results.size(); i++) {
            // orientation = 0、3 表示竖屏，1、2 表示横屏
            float x = (results.get(i).x / height) * 2 - 1;
            float y = (results.get(i).y / width) * 2 - 1;
            float[] point = new float[] {x, -y};

            point[0] = -x;
            point[1] = y;

            oneFace.vertexPoints[2 * i] = point[0];

            oneFace.vertexPoints[2 * i + 1] = point[1];
        }

        return oneFace;

    }
}
