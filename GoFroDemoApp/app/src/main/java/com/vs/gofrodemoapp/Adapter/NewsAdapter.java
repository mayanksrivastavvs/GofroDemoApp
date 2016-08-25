package com.vs.gofrodemoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vs.gofrodemoapp.R;
import com.vs.gofrodemoapp.activity.NewsDetailActivity;
import com.vs.gofrodemoapp.model.Feed;

import java.util.List;

/**
 * Created by mayank on 25-Aug-16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<Feed> feedList;
    private Context context;

    public NewsAdapter(List<Feed> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionTv, contentTv, moreTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTxt);
            contentTv = (TextView) itemView.findViewById(R.id.contentTxt);
            moreTv = (TextView) itemView.findViewById(R.id.moreButton);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Feed feed = feedList.get(position);
        holder.descriptionTv.setText(feed.getDescription());
        holder.contentTv.setText(feed.getContent());
        holder.moreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("url",feed.getLink());
                intent.putExtra("title",feed.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }


}
