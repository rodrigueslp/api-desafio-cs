package com.luizpaulo.apidesafiocs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataUtil {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String converteDateParaString(LocalDateTime data) {

        if(data == null || data.equals("")) return "";

        return data.format(FORMATTER);

    }

    public static long diferencaMinutos(LocalDateTime data1, LocalDateTime data2) {

        Duration between = Duration.between(data1, data2);
        return between.getSeconds() / 60;

    }

}
