package com.daria.weather.simpleweatherapplication.storage.database.entitiy;

import android.arch.persistence.room.ColumnInfo;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.common.BaseEntity;


/**
 * Created by Daria Popova on 06.11.17.
 */
public class WeatherEntity {

    @ColumnInfo(name = Entity.ID_COLUMN)
    private Long id;
    @ColumnInfo(name = Entity.MAIN_COLUMN)
    private String main;
    @ColumnInfo(name = Entity.DESCRIPTION_COLUMN)
    private String description;
    @ColumnInfo(name = Entity.ICON_COLUMN)
    private Long icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherEntity that = (WeatherEntity) o;

        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return icon != null ? icon.equals(that.icon) : that.icon == null;
    }

    @Override
    public int hashCode() {
        int result = main != null ? main.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
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

    public Long getIcon() {
        return icon;
    }

    public void setIcon(Long icon) {
        this.icon = icon;
    }

    public static final class Entity extends BaseEntity {

        public static final String MAIN_COLUMN = "main";
        public static final String DESCRIPTION_COLUMN = "description";
        public static final String ICON_COLUMN = "icon";
    }

}
