package com.example.iamjiangtian.myresume.utils;

import android.util.Log;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by iamjiangtian on 11/28/16.
 */

public class DateHandler {
    public String[] dateToString(Date startDate, Date endDate){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/yyyy");
        //String dateRange = "(" + sdf.format(startDate) + "~" + sdf.format(endDate) + ")";
        String[] dateRange = new String[2];
        dateRange[0] = sdf.format(startDate);
        dateRange[1] = sdf.format(endDate);
        return dateRange;
    }
    public Date[] stringToDate(String startStr, String endStr){
        Date[] res = new Date[2];
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/yyyy");
        try {
            res[0] = sdf.parse(startStr);
        }catch(ParseException e)
        {
            Log.e("TIME", "startTime exception");
        }
        try {
            res[1] = sdf.parse(endStr);
        }catch(ParseException e)
        {
            Log.e("TIME", "endTime exception");
        }
        return res;
    }
}
