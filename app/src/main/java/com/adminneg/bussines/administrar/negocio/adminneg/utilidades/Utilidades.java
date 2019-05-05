package com.adminneg.bussines.administrar.negocio.adminneg.utilidades;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utilidades {
    public static int rotacion=0;
    public long salir=0;
    public static  boolean validaPantalla=true;
    public static  long fechaHoraActual= (long) (new Date().getTime()/1000);
    public static String formatDateTime(Context context, String timeToFormat) {
        String finalDateTime = "";
        SimpleDateFormat iso8601Format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (ParseException e)
            { date = null; }
            if (date != null) {
                long when = date.getTime();
                int flags = 0;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;
                finalDateTime = android.text.format.DateUtils.formatDateTime(context, when + TimeZone.getDefault().getOffset(when), flags);
            }
        }
        return finalDateTime;
    }
}
