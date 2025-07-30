package com.alipay.global.api.tools;

import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateTool {

  public static final String ISO8601 = "yyyy-MM-dd'T'HH:mm:ssZZZ";

  public static String getCurISO8601Time() {
    return DateFormatUtils.format(new Date().getTime(), ISO8601);
  }

  public static String getCurrentTimeMillis() {
    return String.valueOf(System.currentTimeMillis());
  }
}
