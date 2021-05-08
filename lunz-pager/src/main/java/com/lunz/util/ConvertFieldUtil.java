package com.lunz.util;

import java.util.function.BiFunction;

public class ConvertFieldUtil {

	   public static String camelToUnderline(String origin){
		      return stringProcess(
		          origin, (prev, c) -> {
		            if (Character.isLowerCase(prev) && Character.isUpperCase(c)) {
		              return "" + "_" + Character.toLowerCase(c);
		            }
		            return ""+c;
		          }
		      );
		    }

		    public static String underlineToCamel(String origin) {
		      return stringProcess(
		          origin, (prev, c) -> {
		            if (prev == '_' && Character.isLowerCase(c)) {
		              return "" + Character.toUpperCase(c);
		            }
		            if (c == '_') {
		              return "";
		            }
		            return ""+c;
		          }
		      );
		    }

		    public static String stringProcess(String origin, BiFunction<Character, Character, String> convertFunc) {
		      if (origin==null||"".equals(origin.trim())){
		        return "";
		      }
		      String newOrigin = "0" + origin;
		      StringBuilder sb=new StringBuilder();
		      for (int i = 0; i < newOrigin.length()-1; i++) {
		        char prev = newOrigin.charAt(i);
		        char c=newOrigin.charAt(i+1);
		        sb.append(convertFunc.apply(prev,c));
		      }
		      return sb.toString();
		    }
}
