package com.htzw.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.lang.reflect.Type;

import java.util.Date;
import java.util.Map;

/**使用FastJson反序列化自定义属性的时间格式
 *
 * 由于前端传的日期格式比较奇葩 （eg：09-13/2018），如果是以此方式到后台通过@ResquestBody 注解的接收话会报序列化错误。
 *
 * 解决办法
 * 在要进行反序列化的实体类上添加注解@JSONField(deserializeUsing=DateExtraProcessor.class)，其中DateExtraProcessor就是自定义的反序列化处理类。
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/27 10:40
 */
@Slf4j
public class DateExtraProcessor extends AbstractDateDeserializer {
    private DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }

    @Override
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object value) {
        //此处得到是前端传来的所有数据的原始格式字符串
        Object object = parser.input;

        //将json字符串转为map，方便后续操作
        Map map = JSON.parseObject(String.valueOf(object),Map.class);

        //获取对应参数值
        String timestr =(String) map.get(String.valueOf(fieldName));
        if(log.isDebugEnabled()){
            log.debug("time is  {}",timestr);
        }

        //格式化成日期
        Date date = parseStr2Date(timestr);
        return (T)date;
    }
    private  Date parseStr2Date(String source) {
        //使用joda的日期操作类
        return DateTime.parse(source, pattern).toDate();
    }
}
