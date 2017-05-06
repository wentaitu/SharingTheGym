package so.go2.sharingthegym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {

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
