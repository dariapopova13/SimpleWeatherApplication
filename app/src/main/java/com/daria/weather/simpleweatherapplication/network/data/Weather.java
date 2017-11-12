package com.daria.weather.simpleweatherapplication.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class Weather {

    private long id;
    private String main;
    private String description;
    @SerializedName("icon")
    private long iconId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIconId() {
        return iconId;
    }

    public void setIconId(long iconId) {
        this.iconId = iconId;
    }
}
