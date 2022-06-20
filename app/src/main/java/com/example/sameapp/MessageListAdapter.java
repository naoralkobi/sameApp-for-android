package com.example.sameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.sameapp.models.Message;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView messageContent;
        private final TextView time;
        private final TextView date;

        ViewHolder(View itemView) {
            super(itemView);
            messageContent =  itemView.findViewById(R.id.text_gchat_message_me);
            time = itemView.findViewById(R.id.text_gchat_timestamp_me);
            date = itemView.findViewById(R.id.text_gchat_date_me);
        }
    }

    private LayoutInflater mInflater;
    private List<Message> messages;

    // data is passed into the constructor
    MessageListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1){
            view = mInflater.inflate(R.layout.item_message_me, parent, false);
        } else {
            view = mInflater.inflate(R.layout.item_message_other, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (messages != null){
            final Message current = messages.get(position);
            boolean sender = current.isSent();
            if (sender){
                return 1;
            }
        }
        return 0;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (messages != null ){
            final Message current = messages.get(position);
            holder.messageContent.setText(current.getContent());

            String DateAndTime = current.getCreated();
            String[] arrTime = DateAndTime.split(" ", 2);
            holder.time.setText(arrTime[1]);
            holder.date.setText(arrTime[0]);

        }
    }

    public void setMessages(List<Message> m){
        messages = m;
        notifyDataSetChanged();
    }

    // total number of rows
    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        }
        else{
            return 0;
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    //    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
}