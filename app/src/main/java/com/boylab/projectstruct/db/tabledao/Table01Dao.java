package com.boylab.projectstruct.db.tabledao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.boylab.projectstruct.db.table.Table01;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TABLE01".
*/
public class Table01Dao extends AbstractDao<Table01, Long> {

    public static final String TABLENAME = "TABLE01";

    /**
     * Properties of entity Table01.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property A = new Property(1, String.class, "A", false, "A");
        public final static Property I = new Property(2, int.class, "i", false, "I");
        public final static Property L = new Property(3, long.class, "l", false, "L");
        public final static Property F = new Property(4, float.class, "f", false, "F");
        public final static Property D = new Property(5, double.class, "d", false, "D");
        public final static Property B = new Property(6, byte.class, "b", false, "B");
    }


    public Table01Dao(DaoConfig config) {
        super(config);
    }
    
    public Table01Dao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TABLE01\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"A\" TEXT," + // 1: A
                "\"I\" INTEGER NOT NULL ," + // 2: i
                "\"L\" INTEGER NOT NULL ," + // 3: l
                "\"F\" REAL NOT NULL ," + // 4: f
                "\"D\" REAL NOT NULL ," + // 5: d
                "\"B\" INTEGER NOT NULL );"); // 6: b
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TABLE01\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Table01 entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String A = entity.getA();
        if (A != null) {
            stmt.bindString(2, A);
        }
        stmt.bindLong(3, entity.getI());
        stmt.bindLong(4, entity.getL());
        stmt.bindDouble(5, entity.getF());
        stmt.bindDouble(6, entity.getD());
        stmt.bindLong(7, entity.getB());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Table01 entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String A = entity.getA();
        if (A != null) {
            stmt.bindString(2, A);
        }
        stmt.bindLong(3, entity.getI());
        stmt.bindLong(4, entity.getL());
        stmt.bindDouble(5, entity.getF());
        stmt.bindDouble(6, entity.getD());
        stmt.bindLong(7, entity.getB());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Table01 readEntity(Cursor cursor, int offset) {
        Table01 entity = new Table01( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // A
            cursor.getInt(offset + 2), // i
            cursor.getLong(offset + 3), // l
            cursor.getFloat(offset + 4), // f
            cursor.getDouble(offset + 5), // d
            (byte) cursor.getShort(offset + 6) // b
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Table01 entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setA(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setI(cursor.getInt(offset + 2));
        entity.setL(cursor.getLong(offset + 3));
        entity.setF(cursor.getFloat(offset + 4));
        entity.setD(cursor.getDouble(offset + 5));
        entity.setB((byte) cursor.getShort(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Table01 entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Table01 entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Table01 entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}