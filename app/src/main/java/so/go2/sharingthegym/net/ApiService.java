package so.go2.sharingthegym.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import so.go2.sharingthegym.model.uploadModel;

/**
 * 请求接口
 * Created by Rye on 2017/5/6.
 */

public interface ApiService {

    @GET("/start.php")
    Observable<uploadModel> upload(@Query("sn") String sn, @Query("time") String time);
}
