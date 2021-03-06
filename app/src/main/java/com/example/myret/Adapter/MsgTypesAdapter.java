package com.example.myret.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.MsgsTypeWithCount;
import com.example.myret.MsgsActivity;
import com.example.myret.R;

import java.util.ArrayList;
import java.util.List;

;import static android.content.Context.MODE_PRIVATE;

public class MsgTypesAdapter extends RecyclerView.Adapter<MsgTypesAdapter.MsgTypesViewHolder> {

    private Context context;
    private List<MsgsTypeWithCount> msgTypesList=new ArrayList<>();


    public MsgTypesAdapter(Context context, List<MsgsTypeWithCount> msgTypesList) {
        this.context = context;
        this.msgTypesList = msgTypesList;
    }

    @NonNull
    @Override
    public MsgTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MsgTypesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_msg_types, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MsgTypesViewHolder holder, int position) {
        MsgsTypeWithCount msgsTypeWithCount= msgTypesList.get(position);
        MsgTypes msgTypes = msgsTypeWithCount.getMsgTypes();
        holder.tv_desc.setText( msgTypes.getTypeDescription());
        holder.tv_newMsg.setText( String.valueOf(msgsTypeWithCount.getSubCount()));
//        holder.tv_desc.setText("tv_desc" + msgTypes.getTypeDescription());
//        holder.tv_newMsg.setText("tv_newMsg" + msgTypes.getNewMsg());
        SharedPreferences preferences =context.getSharedPreferences("MYPREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MsgsActivity.class);
//                editor.putInt("TypeDescription",msgTypes.getTypeID());
                i.putExtra("TypeDescription",msgTypes.getTypeID());
                context.startActivity(i);
            }
        });

    }

    public void setAllMsgTypes(List<MsgsTypeWithCount> msgTypesList) {
        this.msgTypesList = msgTypesList;
    }

    @Override
    public int getItemCount() {
        return msgTypesList.size();
    }

    public static class MsgTypesViewHolder extends RecyclerView.ViewHolder {

        TextView tv_desc, tv_newMsg;
        CardView cardView;

        public MsgTypesViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_desc = itemView.findViewById(R.id.msg_types);
            tv_newMsg = itemView.findViewById(R.id.newMsg);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
