package com.yash.simplenotes.database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
