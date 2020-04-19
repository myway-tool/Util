package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.dto.PrintDto;

public class PrintStringUtil {

	private static int LEN = 20;
	
	public static List<String> getMsgConsole(List<PrintDto> dataList) {
		List<String> msgList = new ArrayList<>();
		StringBuilder sb = null;
		// header
		msgList.add("\n");
		msgList.add("total  count:" + dataList.size() + "\n");
		
		sb = new StringBuilder();
		sb.append(StringUtils.rightPad("-", LEN, '-')).append("+");
		sb.append(StringUtils.rightPad("-", LEN, '-')).append("\n");
		msgList.add(sb.toString());
		
		sb = new StringBuilder();
		sb.append(StringUtils.rightPad("username", LEN, ' ')).append("|");
		sb.append(StringUtils.rightPad("password", LEN, ' ')).append("\n");
		msgList.add(sb.toString());
		
		sb = new StringBuilder();
		sb.append(StringUtils.rightPad("-", LEN, '-')).append("+");
		sb.append(StringUtils.rightPad("-", LEN, '-')).append("\n");
		msgList.add(sb.toString());
		
		// data
		for (PrintDto data : dataList) {
			sb = new StringBuilder();
			sb.append(StringUtils.rightPad(data.getUsername(), LEN, ' ')).append("|");
			sb.append(StringUtils.rightPad(data.getPassword(), LEN, ' ')).append("\n");
			
			msgList.add(sb.toString());
		}
		
		msgList.add("\n");
		
		return msgList;
	}
	
	public String paddingLeft(String inStr, int length, char padChar) {
		if (inStr.length() >= length) {
			return inStr;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inStr.length()) {
			sb.append(padChar);
		}
		sb.append(inStr);

		return sb.toString();
	}

//	public String paddingRight(String inStr, int length, char padChar) {
//		if (inStr.length() >= length) {
//			return inStr;
//		}
//		StringBuilder sb = new StringBuilder();
//		while (sb.length() < length - inStr.length()) {
//			sb.append(padChar);
//		}
//		sb.append(inStr);
//
//		return sb.toString();
//	}
}
