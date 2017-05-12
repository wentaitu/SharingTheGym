package so.go2.sharingthegym.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit请求客户端
 * Created by Rye on 2017/5/6.
 */

public class RetrofitClient {

    private static final long OK_HTTP_CONNECT_TIME_OUT = 10;//超时
    private static final String URL = "http://120.77.87.78:8080"; //host地址
    private static Retrofit instance;

    public static Retrofit getInstance(){
        if(instance == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(OK_HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .build();
            return new Retrofit
                    .Builder()
                    .baseUrl(URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }else return instance;
    }

    public static <T> T create(Class<T> service){
        return getInstance().create(service);
    }


}
