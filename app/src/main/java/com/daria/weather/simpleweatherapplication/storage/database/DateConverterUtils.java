package com.daria.weather.simpleweatherapplication.storage.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Daria Popova on 06.11.17.
 */

public final class DateConverterUtils {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
