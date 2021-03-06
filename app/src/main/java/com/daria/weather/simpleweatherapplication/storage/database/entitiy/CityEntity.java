package com.daria.weather.simpleweatherapplication.storage.database.entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.common.BaseEntity;


/**
 * Created by Daria Popova on 06.11.17.
 */

@Entity(tableName = CityEntity.Entity.TABLE_NAME)
public class CityEntity {

    @PrimaryKey
    @ColumnInfo(index = true, name = Entity.ID_COLUMN)
    private long id;
    @ColumnInfo(name = Entity.NAME_COLUMN)
    private String name;
    @ColumnInfo(name = Entity.COUNTRY_COLUMN)
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static final class Entity extends BaseEntity {

        public static final String TABLE_NAME = "city";

        public static final String NAME_COLUMN = "name";
        public static final String COUNTRY_COLUMN = "country";

    }
}
