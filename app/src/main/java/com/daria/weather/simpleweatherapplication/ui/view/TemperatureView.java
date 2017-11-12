package com.daria.weather.simpleweatherapplication.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Daria Popova on 11.11.17.
 */

public class TemperatureView extends TextView {

    public static final String DEGREE = "°";

    public TemperatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setTemp(int temp) {
        super.setText(String.valueOf(temp).concat(DEGREE));
    }
}
