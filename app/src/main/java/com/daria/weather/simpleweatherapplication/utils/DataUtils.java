package com.daria.weather.simpleweatherapplication.utils;

import android.content.Context;

import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.Reusable;

/**
 * Created by Daria Popova on 13.11.17.
 */
@Reusable
public final class DataUtils {

    private Context context;

    public DataUtils(Context context) {
        this.context = context;
    }

    public WeatherListEntity getCurrentWeather(List<WeatherListEntity> weatherListEntities) {
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy");
        String current = format.format(time);
        Calendar.getInstance().get(Calendar.DATE);
        String temp;
        Date remove;
        for (WeatherListEntity entity : weatherListEntities) {
            temp = format.format(entity.getDate());
            if (temp.equalsIgnoreCase(current)) {
                return entity;
            }

        }
        return null;
    }

    public String windDirection(double degrees) {
        String direction = "Unknown";
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "north";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "northeast";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "east";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "southeast";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "south";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "southwest";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "west";
        } else if (degrees >= 292.5 && degrees < 337.5) {
            direction = "northwest";
        }
        if (direction.equals("Unknown"))
            return direction;
        int id = context.getResources().getIdentifier(direction.concat("_direction"),
                "string", context.getPackageName());
        return context.getString(id);
    }

    public int getBackground(int weatherId) {

        if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.storm;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.clouds;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.drizzle;
        }

        if (isDay()) {
            if (weatherId >= 200 && weatherId <= 232) {
                return R.drawable.sunny_day;
            } else if (weatherId >= 500 && weatherId <= 531) {
                return R.drawable.rain_day;
            } else if (weatherId >= 600 && weatherId <= 622) {
                return R.drawable.snow_day;
            } else if (weatherId >= 701 && weatherId <= 761) {
                return R.drawable.fog_day;
            } else if (weatherId == 800) {
                return R.drawable.sunny_day;
            } else if (weatherId == 801) {
                return R.drawable.cloud_day;

            } else if (weatherId >= 951 && weatherId <= 957) {
                return R.drawable.sunny_day;
            }
        } else {
            if (weatherId >= 200 && weatherId <= 232) {
                return R.drawable.clear_night;
            } else if (weatherId >= 500 && weatherId <= 531) {
                return R.drawable.rain_night;
            } else if (weatherId >= 600 && weatherId <= 622) {
                return R.drawable.snow_night;
            } else if (weatherId >= 701 && weatherId <= 761) {
                return R.drawable.fog_night;
            } else if (weatherId == 800) {
                return R.drawable.clear_night;
            } else if (weatherId == 801) {
                return R.drawable.clouds;
            } else if (weatherId >= 951 && weatherId <= 957) {
                return R.drawable.clear_night;
            }
        }

        return R.drawable.sunny_day;
    }

    public int getIcon(int weatherId) {

        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_drizzle;
        } else if (weatherId >= 500 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 800) {
            return R.drawable.ic_sunny;
        } else if (weatherId == 801) {
            return R.drawable.ic_cloud;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.ic_sunny;
        }

        return R.drawable.ic_sunny;
    }

    public String getDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = dateFormat.format(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekdayNumber = calendar.get(Calendar.DAY_OF_WEEK);
        String weekdayShortName = getWeekdayName(DayOfWeek.of(weekdayNumber).name(), true);
        return weekdayShortName.concat(" ").concat(formatDate);
    }

    public String getTemp(double temp) {
        final String DEGREE = "°";
        return String.valueOf((int) temp).concat(DEGREE);
    }

    public String getWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String weekday = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).name().toLowerCase();
        return getWeekdayName(weekday, false);
    }

    public String getCurrentTemp(WeatherListEntity weather) {
        if (isDay())
            return getTemp(weather.getTemperature().getDayTemp());
        else return getTemp(weather.getTemperature().getNightTemp());
    }

    public String getHumidity(double humidity) {
        return String.valueOf(humidity).concat(" %");
    }

    public String getWindSpeed(double speed) {
        return String.valueOf((int) speed)
                .concat(" ")
                .concat(context.getString(R.string.wind_speed));
    }


    public String geCloudCover(int clouds) {
        return String.valueOf(clouds).concat(" %");
    }

    public String gePressure(double pressure) {
        return String.valueOf(pressure).concat(" mbar");
    }

    private boolean isDay() {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        int dayEnd = 21;
        int dayStart = 6;

        return hours >= dayStart && hours < dayEnd;
    }

    private String getWeekdayName(String weekday, boolean isShort) {
        weekday = weekday.toLowerCase();
        int id;
        if (isShort) {
            id = context.getResources().getIdentifier(weekday.concat("_name_short"),
                    "string", context.getPackageName());
        } else {
            id = context.getResources().getIdentifier(weekday.concat("_name"),
                    "string", context.getPackageName());
        }
        return context.getString(id);
    }
}
