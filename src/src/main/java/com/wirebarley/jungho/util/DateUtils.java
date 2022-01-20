package com.wirebarley.jungho.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DateUtils {

    public static LocalDateTime timestampToLocalDateTime(Integer timestamp) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId());
    }
}
