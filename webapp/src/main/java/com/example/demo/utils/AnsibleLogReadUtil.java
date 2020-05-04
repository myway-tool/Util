package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class AnsibleLogReadUtil {

	public static void readLogFile(String filepath) {
		
		LinkedHashMap<String, Integer> rtnMap = new LinkedHashMap<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
			boolean isRtnInfoFlg = false;
			boolean isPlay = false;
			
			String playInfo = null; // 정상인 경우 empty
			String rtnInfo = null;
			
			String line = null;
			while(br.ready()) {
				line = br.readLine();
				
				if (line.startsWith("PLAY") && !line.startsWith("PLAY RECAP")) {
					isPlay = true;
					continue;
				}
				if (isPlay) {
					playInfo = line.trim();
					isPlay = false;
				}
				
				if (line.startsWith("TASK") || line.startsWith("PLAY")) {
				}
				
				if (line.startsWith("PLAY RECAP")) {
					isRtnInfoFlg = true;
					continue;
				}
				
				if (isRtnInfoFlg) {
					if (!line.isEmpty()) {
						rtnInfo = line.split(":", -1)[1].trim();
					}
				}
			}
			br.close();
			
			if (rtnInfo != null && !rtnInfo.isEmpty()) {
				String[] vals = rtnInfo.split("\\s", -1);
				for (String val : vals) {
					if (val.isEmpty()) continue;
					String[] rtns = val.split("=", -1);
					rtnMap.put(rtns[0], Integer.parseInt(rtns[1]));
				}
			}
			
			System.out.println(playInfo);
			System.out.println(rtnMap);
//			String[] vals = rtnInfo.split("\\s", -1);
//			LinkedHashMap<String, Integer> rtnMap = new LinkedHashMap<>();
//			for (String val : vals) {
//				if (val.isEmpty()) continue;
//				System.out.println(val);
//				String[] rtns = val.split("=", -1);
//				rtnMap.put(rtns[0], Integer.parseInt(rtns[1]));
//			}
//			
//			System.out.println(rtnMap);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
