package so.go2.sharingthegym.adapt;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import so.go2.sharingthegym.R;
import so.go2.sharingthegym.data.Person;
import so.go2.sharingthegym.help.DataBaseHelp;

/**
 * Created by lusen on 2017/5/8.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TEXT = 2;

    private Activity activity;
    private ArrayList<Person> personList;
    ArrayList<Map<String, String>> mapArrayList;
    /*数据库变量*/
    private DataBaseHelp helper ;
    private SQLiteDatabase database;

    public RecordAdapter(Activity activity,ArrayList<Person> personList,SQLiteDatabase database,DataBaseHelp helper){
        this.activity = activity;
        this.personList = personList;
        this.database = database;
        this.helper = helper;
    }

    public void setPersonList(ArrayList<Person> personList){
        this.personList = personList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(activity);
        MyHolder holder = null;
        if(viewType == TYPE_NORMAL){
            View v = mInflater.inflate(R.layout.item_record,parent,false);
            holder = new MyHolder(v);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
//        用于多布局
//        if(position == 0) return TYPE_HEADER;
//        if (position == 1) return TYPE_TEXT;
//        return TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        deleteData();
        queryData("2");
//        queryData();
        Log.d("RecordActivity",mapArrayList.toString());
        ((MyHolder) holder).record_tv.setText(mapArrayList.get(position).get("name"));
//        ((MyHolder) holder).record_tv.setText(personList.get(position).getThing());
//        ((MyHolder) holder).record_data.setText(personList.get(position).getDate()+" ");
        ((MyHolder) holder).record_data.setText(mapArrayList.get(position).get("address"));
//        ((MyHolder) holder).image.setImageResource(R.drawable.recycle1);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView record_tv;
        private TextView record_data;
        private ImageView image;
        private CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            record_tv= (TextView) itemView.findViewById(R.id.record_tv);
            record_data= (TextView) itemView.findViewById(R.id.record_data);
            image = (ImageView) itemView.findViewById(R.id.record_img);
            cardView = (CardView) itemView.findViewById(R.id.reccord_card);

        }
    }

    /*遍历查询数据 并储存*/
    private void queryData(String id) {
        database = helper.getReadableDatabase();
        Cursor cursor = database.query("person", new String[] { "name",
                "address","time","thing" }, "id>?", new String[] { "0" }, null, null, null);

        mapArrayList = getMapList(cursor);
        database.close();

    }

    private void deleteData() {
        database = helper.getWritableDatabase();
        database.delete("person","id>=?",new String[]{"0"});
        database.close();
    }

    private ArrayList<Map<String, String>> getMapList(Cursor cursor) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();

        //遍历Cursor
        while(cursor.moveToNext()){
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cursor.getString(0));
            map.put("address", cursor.getString(1));
            map.put("time", cursor.getString(2));
            map.put("thing", cursor.getString(3));
            list.add(map);
        }
        return list;
    }
}
