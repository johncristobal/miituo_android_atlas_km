package com.miituo.atlaskm.data;

/**
 * Created by john.cristobal on 12/04/17.
 */

public class VehicleSubtype
{
    public int Id;
    public String Description;

    public VehicleSubtype(int id, String description) {
        Id = id;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}