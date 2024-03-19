package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: PeriodRule.java, v 0.1 2024年03月19日 3:41 PM yanhong Exp $
 **/
public class PeriodRule {

    private String periodType;

    private Integer periodCount;

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriodCount() {
        return periodCount;
    }

    public void setPeriodCount(Integer periodCount) {
        this.periodCount = periodCount;
    }
}
