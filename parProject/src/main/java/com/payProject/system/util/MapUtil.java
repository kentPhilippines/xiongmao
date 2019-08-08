package com.payProject.system.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>关于Map的工具类</p>
 * @author K
 */
public class MapUtil {
	/**
	 * <p>实体类对象转map</p>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try { 
        	for (Field field : fields) {
	            field.setAccessible(true);
	            map.put(field.getName(), field.get(obj));
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
