package com.example.user.tubuyakiapp;

import com.orm.SugarRecord;

/**
 * Created by user on 2016/05/11.
 */
public class Tubuyaki extends SugarRecord {

    //ID(連番)
    public long id;

    //コメント
    public String comment;

    public Tubuyaki() {}

    public Tubuyaki(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
