package com.example.survey.Model;

public class DistrictModel {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private String housenumber;
    private String count;


}
