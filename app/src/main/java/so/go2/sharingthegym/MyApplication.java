package so.go2.sharingthegym;

import android.app.Application;
import android.content.Context;

/**
 * Created by wentai on 17-4-22.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();  //不加onCreat()有红线
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
