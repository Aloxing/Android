package com.cn.miraclestar.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.miraclestar.R;
import com.cn.miraclestar.SendMessageActivity;

import java.util.ArrayList;
import java.util.List;

public class ListMessageAdapter extends RecyclerView.Adapter<ListMessageAdapter.ListMessageViewHolder> {

    private Context context;
    private List<String> _list_message_data;

    public ListMessageAdapter(Context context,List<String> _list_message_data){
        this.context = context;
        this._list_message_data = _list_message_data;
    }

    @NonNull
    @Override
    public ListMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_message, parent, false);
        return new ListMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMessageViewHolder holder, int position) {

        Resources resources = context.getResources();
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.light_gray, null));
//                        return false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                }
                if(event.getAction() == MotionEvent.ACTION_CANCEL){
                    v.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null));
//                        return true;
                }
                return false;
            }

        });

        //修改头像

        //修改名字
        TextView name_text = holder.itemView.findViewById(R.id.list_message_name_text);
        name_text.setText(_list_message_data.get(position));

        //修改内容
        TextView content_text = holder.itemView.findViewById(R.id.list_message_content_text);
        content_text.setText(content_text.getText().toString());

        //修改时间
        TextView time_text = holder.itemView.findViewById(R.id.list_message_time_text);
        time_text.setText(time_text.getText().toString());

        //修改icon状态

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendMessageActivity.class);

                //一个是自己的id,一个是对话的id
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this._list_message_data.size();
    }

    public static class ListMessageViewHolder extends RecyclerView.ViewHolder{

        public ListMessageViewHolder(@NonNull View itemView){
            super(itemView);
        }

    }
}
