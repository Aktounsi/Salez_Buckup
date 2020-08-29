package com.prom.eazy;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

public class DialogBoxError {
    private Activity myActivity;
    AlertDialog dialog;
    ImageView done;
    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    public DialogBoxError(Activity myActivity){
        this.myActivity = myActivity;
    }

    public void startSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        LayoutInflater inflater = myActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_box_error,null));
        //dialog.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        done = (ImageView) dialog.findViewById(R.id.error);
        animateDialog();

    }

    public void dissmissDialog(){
        dialog.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateDialog(){
        Drawable drawable = done.getDrawable();
        if(drawable instanceof AnimatedVectorDrawableCompat){
            avd = (AnimatedVectorDrawableCompat) drawable;
            avd.start();
        }else if(drawable instanceof AnimatedVectorDrawable){
            avd2 = (AnimatedVectorDrawable) drawable;
            avd2.start();
        }
    }

}

