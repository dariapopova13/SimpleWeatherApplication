package com.daria.weather.simpleweatherapplication.utils;

/**
 * Created by Daria Popova on 13/11/17.
 */

public enum DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    private static final DayOfWeek[] ENUMS = DayOfWeek.values();

    public static DayOfWeek of(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
        return ENUMS[dayOfWeek - 1];
    }

}

