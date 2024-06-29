package com.javaweb.Utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<String, String> params, String key, Class<T> tClass) {
		String str = params.getOrDefault(key, null);
		T res = null;
		if(str != null) {
			str = str.trim();
			if(tClass.getTypeName().equals("java.lang.String")) {
				res = tClass.cast(str);
			}
			else if(tClass.getTypeName().equals("java.lang.Long")) {
				res = tClass.cast(str.equals("") ? null : Long.valueOf(str));
			}
			else if(tClass.getTypeName().equals("java.lang.Integer")) {
				res = tClass.cast(str.equals("") ? null : Integer.valueOf(str));
			}
		}
		return res;
	}
}
