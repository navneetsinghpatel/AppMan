package com.ibm.appman;

import android.content.Context;

public class MyContext {

    private static Context myContext;

    public static Context getMyContext() {
        return myContext;
    }

    public static void setMyContext(Context myContext) {
        myContext = myContext;
    }
}
