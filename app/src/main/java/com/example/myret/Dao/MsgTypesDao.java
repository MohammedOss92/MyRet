package com.example.myret.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myret.Modal.MsgTypes;

import java.util.List;

@Dao
public interface MsgTypesDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert_msgtypes (List<MsgTypes>msgTypesList);

    @Query("select * from msgTypes")
    LiveData<List<MsgTypes>> getAllMsgTypes();

    @Query("delete from msgTypes")
    void deleteAllMsgTypes();

    @Query("SELECT COUNT(TypeDescription) FROM msgTypes")
    int getCount();
}
