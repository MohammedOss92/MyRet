package com.example.myret.Modal;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "msg")
public class Msgs {


    @SerializedName("Msg_ID")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "MsgID")
    @NonNull
    private String MsgID;

    @ColumnInfo(name = "MsgDescription")
    @SerializedName("MsgDesc")
    private String MsgDescription;

    @ColumnInfo(name = "TypeDescription")
    @SerializedName("TypeDesc")
    private String TypeDescription;

    @ColumnInfo(name = "newMsg")
    @SerializedName("newMsg")
    private String newMsg;

    public Msgs() {
    }


    public Msgs(String msgDescription, String typeDescription, String newMsg) {
        MsgDescription = msgDescription;
        TypeDescription = typeDescription;
        this.newMsg = newMsg;
    }





    public String getMsgDescription() {
        return MsgDescription;
    }

    public void setMsgDescription(String msgDescription) {
        MsgDescription = msgDescription;
    }


    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        TypeDescription = typeDescription;
    }

    public String getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(String newMsg) {
        this.newMsg = newMsg;
    }

    @Override
    public String toString() {
        return "Msgs{" +
                "MsgID=" + MsgID +
                ", MsgDescription='" + MsgDescription + '\'' +
                ", TypeDescription=" + TypeDescription +
                ", newMsg=" + newMsg +
                '}';
    }
}
