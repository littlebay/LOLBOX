package com.util;

public class StrUtil {
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}
}
