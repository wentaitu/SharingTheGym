package so.go2.sharingthegym;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import so.go2.sharingthegym.model.uploadModel;
import so.go2.sharingthegym.net.ApiService;
import so.go2.sharingthegym.net.RetrofitClient;

public class PayActivity extends AppCompatActivity {

    private static final String TAG = "MY_TAG";
    @BindView(R.id.sheet_bottom)
    BottomSheetLayout bottomSheet;

    @OnClick(R.id.commit) void commit(){
        bottomSheet.showWithSheetView(menuSheetView);
    }

    private MenuSheetView menuSheetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initMenuSheet();
        //测试
        test ();
    }

    private void test() {
        RetrofitClient.create(ApiService.class)
                .upload("01","20")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<uploadModel>() {
                    @Override
                    public void accept(uploadModel uploadModel) throws Exception {
                        Snackbar.make(menuSheetView,uploadModel.getSuccess(),Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(PayActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.getMessage());
                        Toast.makeText(PayActivity.this,"发送失败" + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initMenuSheet() {
        menuSheetView =
                new MenuSheetView(this, MenuSheetView.MenuType.GRID, R.string.choosePay , new MenuSheetView.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(PayActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheet.isSheetShowing()) {
                            bottomSheet.dismissSheet();
                        }
                        return true;
                    }
                });
        menuSheetView.inflateMenu(R.menu.method_pay);
    }
}
