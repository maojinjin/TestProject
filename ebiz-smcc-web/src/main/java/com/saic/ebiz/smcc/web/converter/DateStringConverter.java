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

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 日期转字符串
 * 
 * @author zhangerwei
 */
@Component
public class DateStringConverter extends DateConverterBase implements Converter<Date, String> {

    @Override
    public String convert(Date source) {
        if (null == source) {
            return "";
        }
        return getDateFormat().format(source);
    }

}
