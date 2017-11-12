package com.daria.weather.simpleweatherapplication.utils;

/**
 * Created by Daria Popova on 09.11.17.
 */

public final class MockDataUtils {

    public static String getMockUrl(){
        String url =new UrlUtils.WeatherUrlBuilder()
                .setCityName("Kazan", "ru")
                .setDaysCount(7)
                .setUnitParam("metric")
                .build();
        return url;
    }
}
