package com.boylab.projectstruct.db.helper;

import com.boylab.projectstruct.db.table.Table02;
import com.boylab.projectstruct.db.tabledao.Table02Dao;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public class Table02Helper extends BaseHelper<Table02, Long> {

    public Table02Helper(AbstractDao dao) {
        super(dao);
    }

    public List<Table02> queryByPage(int page){
        return queryBuilder().offset(page * 20).limit(20).list();
    }

    public List<Table02> latestByPage(int page) {
        return queryBuilder().orderAsc(Table02Dao.Properties.Id).offset(page * 20).limit(20).list();
    }
}
