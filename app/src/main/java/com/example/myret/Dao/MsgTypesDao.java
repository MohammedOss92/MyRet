package com.example.myret.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;
import com.example.myret.Modal.MsgsTypeWithCount;

import java.util.List;

@Dao
public interface MsgTypesDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert_msgtypes (List<MsgTypes>msgTypesList);

    @Query("SELECT e.subCount AS subCount, c.* " +
            "FROM msgTypes c " +
            "LEFT JOIN ( " +
            "   SELECT TypeDescription, COUNT(*) subCount " +
            "   from msg " +
            "   GROUP BY TypeDescription) e "+
            "ON e.TypeDescription = c.TypeID")
    LiveData<List<MsgsTypeWithCount>> getAllMsgTypesWithCounts();

    @Query("select * from msgTypes")
    LiveData<List<MsgTypes>> getAllMsgTypes();

    @Query("delete from msgTypes")
    void deleteAllMsgTypes();

    @Query("SELECT COUNT(TypeDescription) FROM msgTypes")
    int getCount();
}
