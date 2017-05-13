package so.go2.sharingthegym.help;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lusen on 2017/5/6.
 */

public class DataBaseHelp extends SQLiteOpenHelper {

    private static String name = "Record.db";
    private static Integer version = 1;

    public DataBaseHelp(Context context) {
        super(context, name, null, version);
    }

    public DataBaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String msqlt = "create table person(id integer primary key autoincrement,name varchar(64),address varchar(64),time date(64),thing varchar(64) )";
        sqLiteDatabase.execSQL(msqlt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
