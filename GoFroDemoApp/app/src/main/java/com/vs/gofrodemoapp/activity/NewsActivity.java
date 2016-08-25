package com.vs.gofrodemoapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vs.gofrodemoapp.Adapter.NewsAdapter;
import com.vs.gofrodemoapp.R;
import com.vs.gofrodemoapp.model.Feed;

import java.util.ArrayList;

/**
 * Created by mayank on 25-Aug-16.
 */
public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ArrayList<Feed> feeds =(ArrayList<Feed>) getIntent().getSerializableExtra("feed");
        setTitle("Top Stories - Delhi News");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        NewsAdapter adapter = new NewsAdapter(feeds,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
