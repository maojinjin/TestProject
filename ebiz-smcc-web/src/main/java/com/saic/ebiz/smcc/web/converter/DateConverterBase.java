/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: TestController.java
 * Author:   zhangerwei
 * Date:     2013年11月20日 上午11:00:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.saic.ebiz.smcc.web.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

/**
 * 日期转换
 * 
 * @author zhangerwei
 */
@Component
public class DateConverterBase {
    private String datePattern = "yyyy-MM-dd";
    private String timePattern = "HH:mm:ss";
    private DateFormat dateFormat = new SimpleDateFormat(datePattern);
    private DateFormat dateTimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the dateTimeFormat
     */
    public DateFormat getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * @param dateTimeFormat the dateTimeFormat to set
     */
    public void setDateTimeFormat(DateFormat dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

}
