package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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


    public static String convertToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static <T> T convertToPOJO(String jsonText, Class<T> t ) throws JsonProcessingException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonText, t);
    }

    public static <T> T convertToPOJOFromFile(String filePath, Class<T> t)
            throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructCollectionType(List.class, Class.forName(t.getName())));
    }

    public static String convertToPrettyJson(String jsonText) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> valueMap = convertToPOJO(jsonText, Map.class);


//      mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//      return mapper.writeValueAsString(jsonText);

        return convertToPrettyJson(valueMap);
    }

    public static String convertToPrettyJson(Object object) throws JsonProcessingException {
//      ObjectMapper mapper = new ObjectMapper();
//      mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//      return mapper.writeValueAsString(object);

        return convertToPrettyJson(object, 2); // default : 2 space
    }

    public static String convertToPrettyJson(Object object, int indentCount) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

//      DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter(padLeft(indentCount), DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);

        String json = mapper.writer(printer).writeValueAsString(object);
        return json;
    }

    public static String convertToPrettyJson(Object object, String indentStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

//      DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter(indentStr, DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);

        String json = mapper.writer(printer).writeValueAsString(object);
        return json;
    }

//  private static String padLeft(String s, int n) {
//      return String.format("%" + n + "s", s);
//  }
    private static String padLeft(int n) {
        return String.format("%" + n + "s", " ");
    }
}
