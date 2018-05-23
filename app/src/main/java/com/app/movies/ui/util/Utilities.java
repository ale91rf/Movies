package com.app.movies.ui.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    private static String IMAGE_BASE = "https://image.tmdb.org/t/p/w500/";

    public static String intToString(int number) {
        return String.valueOf(number);
    }

    public static String transformImagePath(String posterPath) {
        return IMAGE_BASE + posterPath;
    }

    public static String transformDate(String dateString) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
            try {
                Date date = formatter.parse(dateString);
                return yearFormatter.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (NullPointerException n) {
                n.printStackTrace();
            }
        }
        return "";
    }
}
