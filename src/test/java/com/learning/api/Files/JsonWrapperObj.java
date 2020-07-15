package com.learning.api.Files;

import io.restassured.path.json.JsonPath;

public class JsonWrapperObj {
	private static JsonPath js;
	public static JsonPath getObj(String str){
		js = new JsonPath(str);
		return js;
		
	}
	
}
