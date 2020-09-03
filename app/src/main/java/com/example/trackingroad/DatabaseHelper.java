package com.example.trackingroad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="userdetails";
    private static final String TABLE_NAME="user_details";

    private static final String SERIAL="Serial";
    private static final String NAME="Name";
    private static final String USERNAME="Username";
    private static final String EMAIL="Email";
    private static final String PHONE_NUMBER="PhoneNumber";
    private static final String PASSWORD="Password";
    private static final int VERSION_NUMBER= 3 ;
    private Context context;

    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+SERIAL+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255) NOT NULL,"+USERNAME+" TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL,"+PHONE_NUMBER+" INTEGER NOT NULL,"+PASSWORD+" TEXT NOT NULL);";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context,"onCreate ",Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception= "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{

            Toast.makeText(context,"onUpgrade ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception= "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(UserDetails userDetails)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(USERNAME,userDetails.getUsername());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(PHONE_NUMBER,userDetails.getPhoneNumber());
        contentValues.put(PASSWORD,userDetails.getPassword());

        long rowId=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Boolean findPassword(String uname,String pass)
    {

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Boolean result = false;

        if(cursor.getCount()==0){
            Toast.makeText(context,"No data is found",Toast.LENGTH_LONG).show();
        }
        else {

            while (cursor.moveToNext())
            {
                String username=cursor.getString(2);
                String password=cursor.getString(5);

                if(username.equals(uname) && password.equals(pass))
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}


