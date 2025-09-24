package com.musearllm.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class DateTimeUtil {

    private static final ZoneId HK_ZONE_ID = ZoneId.of("Asia/Hong_Kong");

    // Formats
    private static final DateTimeFormatter REGULAR_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter REGULAR_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter LINKED_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    private static final DateTimeFormatter LINKED_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    // Get formats
    public static DateTimeFormatter getDateTimeFormatter() {
        return REGULAR_DATE_TIME_FORMATTER;
    }

    public static DateTimeFormatter getDateFormatter() {
        return REGULAR_DATE_FORMATTER;
    }

    public static DateTimeFormatter getLinkedDateTimeFormatter() {
        return LINKED_DATE_TIME_FORMATTER;
    }

    public static DateTimeFormatter getLinkedDateFormatter() {
        return LINKED_DATE_FORMATTER;
    }
    public static Date getCurrentHongKongDate() {
        return Date.from(getCurrentHongKongTime().toInstant());
    }
    ////////// From String
    // LocalDateTime (Regular)
    public static LocalDateTime parseLocalDateTimeFromString(String dateTime) {
        return LocalDateTime.parse(dateTime, REGULAR_DATE_TIME_FORMATTER);
    }

    // LocalDate (Regular)
    public static LocalDate parseLocalDateFromString(String dateTime) {
        return LocalDate.parse(dateTime, REGULAR_DATE_FORMATTER);
    }

    // from ZonedString
    // LocalDateTime
    // public static LocalDateTime parseLocalDateTimeFromZonedString(String
    // dateTime) {
    // return ZonedDateTime.parse(dateTime, ISO_OFFSET_DATE_TIME).toLocalDateTime();
    // }

    ////////// From LocalDateTime (Regular)
    // String (Linked)
    public static String parseLinkedFormat(LocalDateTime localDateTime) {
        return localDateTime.format(LINKED_DATE_TIME_FORMATTER);
    }

    // String (Regular)
    public static String parseStringFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(REGULAR_DATE_TIME_FORMATTER);
    }

    ////////// From LocalDate (Regular)
    // String (Regular)
    public static String parseStringFromLocalDate(LocalDate localDate) {
        return localDate.format(REGULAR_DATE_FORMATTER);
    }

    ////////// From Date
    // LocalDateTime (Regular)
    // public static LocalDateTime parseDateTimeFromDate(Date date) {
    // return LocalDateTime.ofInstant(date.toInstant(),
    ////////// ZoneId.of("Asia/Hong_Kong"));
    // }

    /////// Get now
    public static LocalDateTime getNow() {
        return LocalDateTime.now(HK_ZONE_ID);
    }
    // Get current date
    public static ZonedDateTime getCurrentHongKongTime() {
        return ZonedDateTime.now(HK_ZONE_ID);
    }

    // Convert LocalDateTime to HKT
    public static ZonedDateTime toHongKongTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(HK_ZONE_ID);
    }
}
