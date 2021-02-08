package com.example.myret.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "msgTypes")
public class MsgTypes {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TypeID")
    @SerializedName("TypeID")
    private int TypeID;

    @ColumnInfo(name = "TypeDescription")
    @SerializedName("TypeDescription")
    private String TypeDescription;

    @ColumnInfo(name = "newMsg")
    @SerializedName("newMsg")
    private String newMsg;

    public MsgTypes(int typeID, String typeDescription, String newMsg) {
        TypeID = typeID;
        TypeDescription = typeDescription;
        this.newMsg = newMsg;
    }

    public MsgTypes() {
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
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
        return "MsgTypes{" +
                "TypeID=" + TypeID +
                ", TypeDescription='" + TypeDescription + '\'' +
                ", newMsg='" + newMsg + '\'' +
                '}';
    }
}
