package com.vs.gofrodemoapp.model;

import java.io.Serializable;

/**
 * Created by mayank on 25-Aug-16.
 */
public class ResponseData implements Serializable {

    private String title;

    private Feed[] feed;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Feed[] getFeed ()
    {
        return feed;
    }

    public void setFeed (Feed[] feed)
    {
        this.feed = feed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", feed = "+feed+"]";
    }
}
