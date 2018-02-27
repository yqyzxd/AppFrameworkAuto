package com.wind.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.wind.data.base.bean.LoginUser;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "xkd_db";

    private static final int VERSION = 1;

    @Inject
    public  DbOpenHelper(@NonNull final Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // Better than static final field -> allows VM to unload useless String
    // Because you need this string only once per application life on the device

    @Override
    public void onCreate(final SQLiteDatabase db) {

        db.execSQL(LoginUser.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // no impl
    }
}