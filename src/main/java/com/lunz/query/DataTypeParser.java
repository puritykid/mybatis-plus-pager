package com.lunz.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTypeParser {

    // 解析数据类型
    public static Map<String, Object> Parse(Class<?> type, String propertyName) throws Exception {
        List<String> properties = new ArrayList<String>();

        Object propertyType = Parse(type, propertyName, properties);

        String formatPropertyName = String.join(".", properties);

        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("propertyType", propertyType);
        propertyMap.put("propertyName", formatPropertyName);
        return propertyMap;
    }

    private static Object Parse(Class<?> type, String propertyName, List<String> properties) throws Exception {
        String name = propertyName;
        int index = propertyName.indexOf(".");
        if (index >= 0) {
            name = propertyName.substring(0, index);
        }
        System.out.println(type);
        Field propertyInfo = type.getDeclaredField(name);
        if (propertyInfo == null) {
            throw new Exception("未能在 {type.FullName} 类中找到对应的属性 {propertyName}。");
        }

        properties.add(propertyInfo.getName());

        return index < 0
                ? propertyInfo.getType()
                : Parse(propertyInfo.getType(), propertyName.substring(index + 1), properties);
    }

}
