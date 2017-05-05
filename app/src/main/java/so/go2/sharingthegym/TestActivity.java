package so.go2.sharingthegym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import so.go2.sharingthegym.net.MyHttpURL;

public class TestActivity extends AppCompatActivity {

    private MapView mapView;
    private AMap amap;  //地图对象
    private Button send;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        mapView = (MapView) findViewById(R.id.maps);
//        mapView.onCreate(savedInstanceState);// 此方法必须重写
//        amap = mapView.getMap();

//        LatLng latLng = new LatLng(39.9081728469, 116.3867845961);
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.draggable(true);
//        //markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_send));
//        Marker marker = amap.addMarker(markerOption);
//        //marker.setRotateAngle(0);
        //"http://139.199.63.27/cs.php"
        send = (Button) findViewById(R.id.sendData);
        msg = (TextView) findViewById(R.id.showMsg);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHttpURL.get("http://139.199.63.27/cs.php", new MyHttpURL.Callback() {
                    @Override
                    public void onResponse(String response) {
                        msg.setText(response);
                    }
                });
            }
        });

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }

}
