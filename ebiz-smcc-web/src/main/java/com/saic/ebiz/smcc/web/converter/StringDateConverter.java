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

import com.ibm.framework.exception.BaseException;

/**
 * 字符串转日期
 * 
 * @author zhangerwei
 */
@Component
public class StringDateConverter extends DateConverterBase implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (source == null) {
            return null;
        }
        String trim = source.trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return source.contains(":") ? getDateTimeFormat().parse(trim) : getDateFormat().parse(trim);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("无效的日期格式：" + trim,e);
        }
    }

}
