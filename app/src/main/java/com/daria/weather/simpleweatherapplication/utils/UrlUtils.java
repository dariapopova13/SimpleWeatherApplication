package com.daria.weather.simpleweatherapplication.utils;


import android.content.Context;
import android.net.Uri;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class UrlUtils {

    public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/";
    public static final String WEATHER_BASE_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";

    private static final String WEATHER_KEY_PARAM = "APPID";
    private static final String WEATHER_QUERY_PARAM = "q";
    private static final String WEATHER_DAYS_COUNT_PARAM = "cnt";
    private static final String WEATHER_CITY_ID_PARAM = "id";
    private static final String WEATHER_LATITUDE_PARAM = "lat";
    private static final String WEATHER_LONGITUDE_PARAM = "lon";
    private static final String WEATHER_ZIP_PARAM = "zip";
    private static final String WEATHER_MODE_PARAM = "mode";
    private static final String WEATHER_UNITS_PARAM = "units";
    private static final String WEATHER_API_KEY = "9b8203c6b9b90ba0d77e5b07f943d216";

    public static String getUrlFromPreferences(Context context){
        String city = PreferencesUtils.getCity(context);
        String units = PreferencesUtils.getMetric(context);
        String result = new WeatherUrlBuilder()
                .setCityName(city)
                .setDaysCount(7)
                .setUnitParam(units)
                .build();

        return result;
    }

    public static class WeatherUrlBuilder {

        private Uri uri;

        public String getUrlFromPreferences() {
//            WeatherUrlBuilder builder = new WeatherUrlBuilder()
//                    .setCityName(PreferencesUtils.getCity(context))
//                    .seD

            return "";
        }

        public WeatherUrlBuilder() {
            uri = Uri.parse(WEATHER_BASE_API_URL).buildUpon()
                    .appendQueryParameter(WEATHER_KEY_PARAM, WEATHER_API_KEY)
                    .appendQueryParameter(WEATHER_MODE_PARAM, "json")
                    .build();
        }

        public WeatherUrlBuilder setCityName(String cityName, String countryCode) {
            cityName = cityName.concat(",").concat(countryCode);
            addToUri(WEATHER_QUERY_PARAM, cityName);
            return this;
        }

        public WeatherUrlBuilder setCityName(String cityName) {
            addToUri(WEATHER_QUERY_PARAM, cityName);
            return this;
        }

        public WeatherUrlBuilder setDaysCount(int cnt) {
            addToUri(WEATHER_DAYS_COUNT_PARAM, String.valueOf(cnt));
            return this;
        }

        public WeatherUrlBuilder setCityId(long cityId) {
            addToUri(WEATHER_CITY_ID_PARAM, String.valueOf(cityId));
            return this;
        }

        public WeatherUrlBuilder setLatLonParam(double lat, double lon) {
            addToUri(WEATHER_LATITUDE_PARAM, String.valueOf(lat));
            addToUri(WEATHER_LONGITUDE_PARAM, String.valueOf(lon));
            return this;
        }

        public WeatherUrlBuilder setZipParam(long zipCode, String countryCode) {
            String value = zipCode + "," + countryCode;
            addToUri(WEATHER_ZIP_PARAM, value);
            return this;
        }

        public WeatherUrlBuilder setUnitParam(String units) {
            addToUri(WEATHER_UNITS_PARAM, units);
            return this;
        }


        public String build() {
            String uriString = uri.toString();
            uriString = uriString.replaceAll("%2C", ",");
            return uriString;
        }

        private void addToUri(String param, String value) {
            uri = uri.buildUpon()
                    .appendQueryParameter(param, value)
                    .build();
        }

    }
}
