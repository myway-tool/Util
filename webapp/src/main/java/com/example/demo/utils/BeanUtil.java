package com.example.demo.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanUtil {

	private static String UNDER_BAR = "_";
	private static String REQ_AZ = "[A-Z]";
	
	// AAA_BBB ----> aaaBbb
	// _BBB ----> Bbb
	public static String toDtoField(String columnName){
		StringBuilder sb = null;
		if (columnName != null) {
			sb = new StringBuilder();
			// under bar, ,space, minus ---> under bar
			final char[] chars = columnName.toLowerCase().replaceAll("[_ -]", "_").toCharArray();
			boolean flag = false;
			for (final char c : chars) {
				if(c == '_') {
					flag = true;
					continue;
				}
				if (flag) {
					sb.append(Character.toUpperCase(c));
					flag = false;
				} else {
					sb.append(c);
				}
			}
		}
		return sb == null ? null : sb.toString();
	}
	
	public static String toTableColumn(String dtoFieldName) {
		StringBuilder sb = null;
		if (dtoFieldName != null) {
			sb = new StringBuilder();
			
			final char[] chars = dtoFieldName.toLowerCase().toCharArray();
			for (final char c : chars) {
				if ( c >= 'A' & c <='Z') {
					sb.append(UNDER_BAR);
				}
				sb.append(Character.toUpperCase(c));
			}			
		}
		return sb == null ? null : sb.toString();
	}
}
