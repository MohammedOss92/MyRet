package com.example.myret.Modal;

import androidx.room.Embedded;

public class MsgsTypeWithCount {

    @Embedded
    private MsgTypes msgTypes;

    private int subCount;

    public MsgsTypeWithCount() {
    }

    public MsgsTypeWithCount(MsgTypes msgTypes, int subCount) {
        this.msgTypes = msgTypes;
        this.subCount = subCount;
    }

    public MsgTypes getMsgTypes() {
        return msgTypes;
    }

    public void setMsgTypes(MsgTypes msgTypes) {
        this.msgTypes = msgTypes;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }
}
