package com.luizpaulo.apidesafiocs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public static String converteDateParaString(Date data) {

        if(data == null || data.equals("")) return "";

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return df.format(data);

    }

}
