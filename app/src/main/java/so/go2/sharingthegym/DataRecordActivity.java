package so.go2.sharingthegym;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

import so.go2.sharingthegym.adapt.RecordAdapter;
import so.go2.sharingthegym.data.Person;
import so.go2.sharingthegym.help.DataBaseHelp;

/**
 * Created by lusen on 2017/5/6.
 */

public class DataRecordActivity extends AppCompatActivity{

    /*数据库变量*/
    private DataBaseHelp helper ;
    private SQLiteDatabase db;
    private ContentValues contentValues;

    private RecyclerView mRecordRecycle;
    private RecordAdapter mRecordAdapter;
    private ArrayList<Person> personList;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        addData();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.record_actionbar, menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addData(){
        Date date = new Date(20170509);
        Date date1 = new Date(20160101);
        Person person = new Person("luuluu",2,"caonom",date,"lujiazai");
        Person person1 = new Person("lala",3,"www",date1,"ererer");
//        deleteData();
        insertData("jlkj","1");
        insertData("了科技离开","2");
        personList.add(person);
        personList.add(person1);
        mRecordAdapter.setPersonList(personList);
        mRecordRecycle.setAdapter(mRecordAdapter);
    }

    private void initView(){
        helper = new DataBaseHelp(this);
        personList = new ArrayList<Person>();
        mRecordRecycle = (RecyclerView) findViewById(R.id.recycle_recode);
        mRecordRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecordAdapter = new RecordAdapter(this,personList,db,helper);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*插入数据*/
    //TODO 更改入口参数为preson对象
    private void insertData(String tempName,String id) {
        contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",tempName);
        contentValues.put("address","lass");
        contentValues.put("time","201705013");
        contentValues.put("thing","草傻逼");
        db = helper.getWritableDatabase();
        db.insert("person",null,contentValues);
        db.close();
    }
    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
}
