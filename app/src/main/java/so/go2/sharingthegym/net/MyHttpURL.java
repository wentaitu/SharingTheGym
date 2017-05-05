package so.go2.sharingthegym.net;

/**
 * Created by lusen on 2017/2/6.
 */
import android.os.Handler;

public class MyHttpURL {
    public interface Callback{
        void onResponse(String response);
    }
    public static void get(final String url, final Callback callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = NetUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }

}


