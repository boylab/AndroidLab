package com.boylab.projectstruct.db.helper;

import com.boylab.projectstruct.db.table.Table01;
import com.boylab.projectstruct.db.tabledao.Table01Dao;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public class Table01Helper extends BaseHelper<Table01, Long> {

    public Table01Helper(AbstractDao dao) {
        super(dao);
    }

    public List<Table01> queryByPage(int page){
        return queryBuilder().offset(page * 20).limit(20).list();
    }

    public List<Table01> latestByPage(int page) {
        return queryBuilder().orderAsc(Table01Dao.Properties.Id).offset(page * 20).limit(20).list();
    }
}
