package com.vs.gofrodemoapp.model;

import java.io.Serializable;

/**
 * Created by mayank on 25-Aug-16.
 */
public class Feed implements Serializable {

    private String content;

    private String description;

    private String link;

    private String type;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", description = "+description+", link = "+link+", type = "+type+"]";
    }
}
