package com.ftninformatika.zavrsni_projekat.net;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MyHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="ormlite.db";
    private static final int DATABASE_VERSION=1;
    private Dao<Search,Integer>searchDao=null;


    public MyHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Search.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Search.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Dao<Search,Integer>getSearchDao() throws SQLException{
        if (searchDao==null){
            searchDao=getDao(Search.class);
        }
        return searchDao;
    }



    @Override
    public void close() {
        super.close();
        searchDao=null;
    }
}