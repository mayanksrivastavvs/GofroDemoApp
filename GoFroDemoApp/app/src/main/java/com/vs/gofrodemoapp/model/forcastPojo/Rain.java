package com.vs.gofrodemoapp.model.forcastPojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveen on 26-08-2016.
 */
public class Rain
{
@SerializedName("3h")
    private String hour3;

    public String getHour3() {
        return hour3;
    }

    public void setHour3(String hour3) {
        this.hour3 = hour3;
    }



}
