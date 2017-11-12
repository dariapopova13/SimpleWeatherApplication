package com.daria.weather.simpleweatherapplication.storage.database.entitiy;

import android.arch.persistence.room.ColumnInfo;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.common.BaseEntity;


/**
 * Created by Daria Popova on 06.11.17.
 */
public class WeatherEntity {

    @ColumnInfo(name = Entity.ID_COLUMN)
    private int id;
    @ColumnInfo(name = Entity.MAIN_COLUMN)
    private String main;
    @ColumnInfo(name = Entity.DESCRIPTION_COLUMN)
    private String description;
    @ColumnInfo(name = Entity.ICON_COLUMN)
    private long icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherEntity that = (WeatherEntity) o;

        if (id != that.id) return false;
        if (icon != that.icon) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (icon ^ (icon >>> 32));
        return result;
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

    public long getIcon() {
        return icon;
    }

    public void setIcon(long icon) {
        this.icon = icon;
    }

    public static final class Entity extends BaseEntity {

        public static final String MAIN_COLUMN = "main";
        public static final String DESCRIPTION_COLUMN = "description";
        public static final String ICON_COLUMN = "icon";
    }

}
