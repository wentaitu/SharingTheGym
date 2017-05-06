package so.go2.sharingthegym;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.zxing.client.android.decode.CaptureActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import so.go2.sharingthegym.permission.CheckPermissionsActivity;

public class MainActivity extends CheckPermissionsActivity
        implements
            LocationSource,   //提供位置数据的接口
            AMapLocationListener, //定位回调接口
            NavigationView.OnNavigationItemSelectedListener {
    private static final int SCAN = 0;

    // TODO: 17-4-22  3个运行时权限处理(位置、外部存储、PHONE)

    private MapView mapView;   //地图控件，还有另一包有这两类                 //定义为null, 与不定义的区别
    private AMap amap;  //用于获取AMap地图对象及其操作方法与接口

    private AMapLocationClient mLocationClient = null; //定位发起端
    private AMapLocationClientOption mLocationOption = null;  //定位参数
    private OnLocationChangedListener mListener = null;  //定位回调监听器
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 当Activity唤醒时调用地图唤醒，此方法必须重写
        initAMap();  //进行地图处理

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.plan) {

        } else if (id == R.id.fitnessGuide) {

        } else if (id == R.id.wallet) {

        } else if (id == R.id.scan) {
            //二维码扫描
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent,SCAN);
        } else if (id == R.id.invite) {

        } else if (id == R.id.friends) {

        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //*****************************************************
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //重载这个方法时必须调用父类的这个方法，用于MapView保存地图状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //当Activity暂停的时候调用地图暂停
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //重载这个方法时必须调用父类的这个方法
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当Activity销毁时调用地图的销毁
        mapView.onDestroy();
    }

    //*********************************************************************************
    private void initAMap() {
        if (amap == null) {
            amap = mapView.getMap();  //获取地图控制器AMap对象
        }
        //设置地图UI 定位按钮是否可见 缩放按钮是否可见
        UiSettings settings = amap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setZoomControlsEnabled(false);
        settings.setLogoPosition(0);

        //设置显示定位按钮，可点击
        amap.setLocationSource(this);
        //是否可触发定位并显示定位层（是否打开定位图层）
        amap.setMyLocationEnabled(true);

        //定位小图标，默认蓝点，可自定义图片 定位（当前位置）的绘制样式类
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //设置定位（当前位置）的icon图标
        //myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

        initLoc();
        initAddMarker();
    }

    private void initLoc() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    //****************************************************************************接口LocationSource
    @Override
    public void activate(OnLocationChangedListener listener) {
        //激活位置接口
        mListener = listener;
    }

    @Override
    public void deactivate() {
        //处理定位更新的接口
        mListener = null;

    }
    //*********************************************************************接口AMapLocationListener定位回调监听，当定位完成后调用此方法
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    amap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    amap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(amapLocation);
                    //添加图钉
                    amap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getProvince() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
                    Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());

                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    //自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {

        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
        options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        //位置
        options.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + ""
                + amapLocation.getCity() +  "" + amapLocation.getDistrict() + ""
                + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        //标题
        options.title(buffer.toString());
        //子标题
        options.snippet("Amap");
        //设置多少帧刷新一次图片资源
        options.period(60);
        return options;
    }

    private void initAddMarker() {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_send));
        markerOption.position(new LatLng(106.604471, 29.532775));
        markerOption.draggable(true);  //设置Marker覆盖物是否可拖拽
        Marker marker = amap.addMarker(markerOption);

//        amap.addMarker(markerOption);
//        markerOption.position(new LatLng(108.604471, 28.532775));
//        amap.addMarker(markerOption);
//        markerOption.position(new LatLng(108.604471, 28.832775));
//        amap.addMarker(markerOption);
//        markerOption.position(new LatLng(106.604471, 29.732775));
//        amap.addMarker(markerOption);
//        markerOption.position(new LatLng(110.604471, 31.532775));
//        amap.addMarker(markerOption);
    }

    //二维码扫描成功后的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SCAN && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            //msg即为二维码中获得的信息
            String msg = bundle.getString("result");
            Intent intent = new Intent(this,PayActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

