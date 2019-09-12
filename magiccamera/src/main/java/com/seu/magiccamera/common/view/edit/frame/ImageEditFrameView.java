package com.seu.magiccamera.common.view.edit.frame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import com.seu.magiccamera.common.view.edit.ImageEditFragment;
import com.seu.magicfilter.display.MagicImageDisplay;

@SuppressLint("ValidFragment")
public class ImageEditFrameView extends ImageEditFragment{

	public ImageEditFrameView(Context context, MagicImageDisplay magicDisplay) {
		super(context, magicDisplay);

	}

	@Override
	protected boolean isChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onDialogButtonClick(DialogInterface dialog) {
		// TODO Auto-generated method stub
		
	}
}
