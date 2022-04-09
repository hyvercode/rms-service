package com.hyvercode.rms.helper.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeConverter {
    public static final String PATTERN_YYYY_MM_DD = "yyyyMMdd";
    public static final String PATTERN_HH_MM_SS = "HHmmss";
    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter FORMATTER_HH_MM_SS = DateTimeFormatter.ofPattern("HHmmss");

    private DateTimeConverter() {
    }

    public static Timestamp convertStringToTimestamp(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = formatter.parse(stringDate);
        return new Timestamp(date.getTime());
    }

    public static String convertDateToString(Timestamp inputDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(inputDate.getTime());
        return formatter.format(date);
    }

    public static Timestamp convertStringToTimestamp(String input, String desiredFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(desiredFormat);
        Date date = formatter.parse(input);
        return new Timestamp(date.getTime());
    }

    public static String convertTimeStampToStringFormat(Timestamp timestamp, String format) {
        return timestamp != null ? (new SimpleDateFormat(format)).format(timestamp.getTime()) : null;
    }

    public static String getTransactionDate(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_YYYY_MM_DD);
    }

    public static String getTransactionTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_HH_MM_SS);
    }
}