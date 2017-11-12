package com.daria.weather.simpleweatherapplication.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daria Popova on 11.11.17.
 */

public class WeekdayView extends TextView {

    public WeekdayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).name();
        setText(weekday);
    }

    private enum DayOfWeek {
        MONDAY,

        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        private static final DayOfWeek[] ENUMS = DayOfWeek.values();

        public static DayOfWeek of(int dayOfWeek) {
            if (dayOfWeek < 1 || dayOfWeek > 7) {
                throw new IllegalArgumentException("Invalid value for DayOfWeek: " + dayOfWeek);
            }
            return ENUMS[dayOfWeek - 1];
        }
    }
}
