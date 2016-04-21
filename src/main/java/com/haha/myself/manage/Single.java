package com.haha.myself.manage;

import android.content.Context;

/**
 * single instance
 */
public class Single {

    private static Single single;

    private Context context;

    private Single(Context context){
        this.context = context;
    }

    public static Single getInstance(Context context){
        if(single == null)
            single = new Single(context);
        return single;
    }
}
