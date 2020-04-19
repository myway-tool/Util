package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T toObjectFromString(String jsonText, Class<T> clazz) {
		T t = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			t = mapper.readValue(jsonText, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static String toStringFromObject(Object obj) {
		String jsonText = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonText = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonText;
	}
	
	public static String toStringFromFile(String filepath) {
		String jsonText = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			jsonText = mapper.writeValueAsString(toObjectFromString(readFile(filepath), List.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonText;	
	}
	
	private static String readFile(String filepath) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(filepath), StandardCharsets.UTF_8));
			
			while(br.ready()) {
				sb.append(br.readLine().trim());
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return sb.toString();
	}
}
