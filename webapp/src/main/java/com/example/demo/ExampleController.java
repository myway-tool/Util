package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PrintDto;
import com.example.demo.service.AppRoleService;
import com.example.demo.utils.PrintStringUtil;

@RestController
@EnableAutoConfiguration
public class ExampleController {

	@Autowired
	private AppRoleService appRoleService;
	
    @RequestMapping("/")
    String testJsonData() {
    	
        return "Hello World!";
    }
    
    @RequestMapping("/test/{kindcode}")
    @ResponseBody
    List<String> testJpaData(@PathVariable String kindcode, 
    	    Map<String, Object> data) {
    	
    	System.out.println("[kindcode]" + kindcode);
    	System.out.println(data);
    	
    	String filepath = "json/temp01.json";
//    	return JsonUtil.toStringFromFile(filepath);
    	
    	List<String> consoleDisplayList = new ArrayList<>();    	
    	List<PrintDto> tmpList = new ArrayList<>();
    	tmpList.add(new PrintDto("useraa0001", "passwd0001"));
    	tmpList.add(new PrintDto("useraa0002", "passwd0002"));
    	tmpList.add(new PrintDto("useraa0003", "passwd0003"));
    	consoleDisplayList = PrintStringUtil.getMsgConsole(tmpList);
    	
    	return consoleDisplayList;
    	
    	
    }
    
}