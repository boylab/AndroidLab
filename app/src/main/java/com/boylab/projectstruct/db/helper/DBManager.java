package com.boylab.projectstruct.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boylab.projectstruct.db.tabledao.DaoMaster;
import com.boylab.projectstruct.db.tabledao.DaoSession;

/**
 *  DBManager 作为数据库唯一入口
 * 每一个Util都管理着数据库中的一个表，由GreenDaoManager进行统一管理
 */
public class DBManager {

    public final String DB_NAME = "boylab-db";

    private static DBManager instance;
    private SqliteOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private DBManager(){

    }
    public static DBManager newInstance(){
        if (instance==null){
            synchronized (DBManager.class){
                if (instance==null){
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    public void setDataBase(Context context){
        /**
         * 初始化数据库
         * 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
         * 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
         */
        devOpenHelper = new SqliteOpenHelper(context, DB_NAME, null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     *  DBManager 作为数据库唯一入口
     * 每一个Helper都管理着数据库中的一个表，由DBManager进行统一管理
     */
    private Table01Helper table01Helper;
    public synchronized Table01Helper getTable01Helper() {
        if (table01Helper == null){
            table01Helper = new  Table01Helper(daoSession.getTable01Dao());
        }
        return table01Helper;
    }

    /**
     * Table02数据库表操作
     */
    private Table02Helper table02Helper;
    public synchronized Table02Helper getTable02Helper() {
        if (table02Helper == null){
            table02Helper = new  Table02Helper(daoSession.getTable02Dao());
        }
        return table02Helper;
    }

}
