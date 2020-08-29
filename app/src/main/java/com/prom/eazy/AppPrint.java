package com.prom.eazy;

import android.app.Application;

import com.mazenrashed.printooth.Printooth;

public class AppPrint extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Printooth.INSTANCE.init(this);

    }
}
