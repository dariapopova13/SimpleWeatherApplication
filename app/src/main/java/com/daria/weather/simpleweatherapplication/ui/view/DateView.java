package com.daria.weather.simpleweatherapplication.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class DateView extends TextView {


    public void setDate(Date date) {
        // FIXME: 13.11.17 replace
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = dateFormat.format(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekdayNumber = calendar.get(Calendar.DAY_OF_WEEK);
        String weekdayShortName = DayOfWeek.of(weekdayNumber).shortName();
        setText(weekdayShortName.concat(" ").concat(formatDate));
    }

    public DateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
