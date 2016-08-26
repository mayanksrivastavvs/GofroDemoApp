package com.vs.gofrodemoapp.model.forcastPojo;

/**
 * Created by Naveen on 26-08-2016.
 */
public class Sys
{
    private String pod;

    private String population;

    public String getPod ()
    {
        return pod;
    }

    public void setPod (String pod)
    {
        this.pod = pod;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pod = "+pod+",population = "+population+"]";
    }
}

