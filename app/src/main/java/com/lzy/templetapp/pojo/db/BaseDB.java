package com.lzy.templetapp.pojo.db;


import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * @author lzy
 * create at 2018/11/5 19:38
 **/
public class BaseDB implements Serializable {

    public static final String IDkey = "id";

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
