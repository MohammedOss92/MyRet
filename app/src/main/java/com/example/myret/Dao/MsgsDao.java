package com.example.myret.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;

import java.util.List;
@Dao
public interface MsgsDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insert_msgs (Msgs msgs);

//    @Insert
//    void insertmsgs(List<Msgs>msgsList);

    @Query("Select * from msg where TypeDescription=:TypeDescription")
    LiveData<List<Msgs>> getAllMsgsDao(int TypeDescription);

    @Query("SELECT COUNT(MsgDescription) FROM msg where TypeDescription=:TypeDescription")
    int getCount(int TypeDescription);

    @Query("delete from msg")
    void deleteAllmessage();

    @Query("select * from msg where TypeDescription=:TypeDescription and MsgDescription like '%'|| :filterValue  || '%'")
     LiveData<List<Msgs>> getMsgFilterDao (int TypeDescription,String filterValue);

}
