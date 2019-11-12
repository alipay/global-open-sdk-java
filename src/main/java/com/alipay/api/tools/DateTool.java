package com.alipay.api.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class DateTool {

    public final static String ISO8601= "yyyy-MM-dd'T'HH:mm:ssZZZ";

    public static String getCurISO8601Time() {
        return DateFormatUtils.format(new Date().getTime(), ISO8601);
    }

}
