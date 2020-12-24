package com.boylab.projectstruct.util;

import java.util.Date;

public class StringUtil {

    //F   “年-月-日”格式   2007-10-27
    public static String formatF(Date date){
        return String.format("%tF", date);
    }

    //T   “HH:MM:SS”格式（24时制）  14:28:16
    public static String formatT(Date date){
        return String.format("%tT", date);
    }

    //FT   “年-月-日 HH:MM:SS”格式（24时制）  2007-10-27 14:28:16
    public static String formatFT(Date date){
        return String.format("%1$tF %1$tT ", date);
    }




}
