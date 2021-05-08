package com.lunz.query;

public class StringUtil {
	
	public static boolean IsNullOrWhiteSpace(String str) {
		if (str==null || str.length()==0) {
			return true;
		}
		return false;
	}
	
}
