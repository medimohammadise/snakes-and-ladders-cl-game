package my.practice.codechallenges.puzzle.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class GameConfigurationPorcessor {
	private static final String EXTRACTOR_SCRIPT = "var fun = function(raw) { " + "var json = JSON.parse(raw); "
			+ "return json;}";

	public static void main(String args[]) {
		readDataFromFile("configuration", "inputConfigFile.json",false);
	}

private static String getJsonFromMap(Map<String, Object> map) {
		
	StringBuilder sb = new StringBuilder();
	boolean multipleValue=false;
	for (String key : map.keySet()) {
		Object value = map.get(key);
		if (value instanceof Map<?, ?>) {
			
			multipleValue=true;
			value = getJsonFromMap((Map<String, Object>) value);
			
		} else if (value instanceof ArrayList) {
			ArrayList valueList = ((ArrayList) value);
			/*if (sb.length() > 0)
				sb.append(",");
			 sb.append("\"" + key + "\"" + ":" +"[{" );*/
			
			multipleValue=true;
			for (int i = 0; i < valueList.size(); i++) {
				
				if (valueList.get(i) instanceof Map<?, ?>) {
					value = getJsonFromMap((Map<String, Object>) valueList.get(i));
				}
				
			}
			/*if (sb.length() > 0)
				sb.append(",");
			sb.append("\"" + key + "\"" + ":" +"\"" + value + "\"" );
			sb.append("]}");*/

		}
		
			if (sb.length() > 0)
				sb.append(",");
			if (!"".equals(value) && !multipleValue)
			   sb.append("\"" + key + "\"" + ":" +"\"" + value + "\"" );
			else
			{
				
				sb.append("\"" + key + "\"" + ":" +"{"+ value + "}" );
				multipleValue=false;
			}
	}
	
	return sb.toString();

	
	}


	public static boolean saveStateIntoJson(String basePath, String filename, Map<String, Object> configMap,String playerId) {
		/*
		 * for (Map.Entry entry : configMap.entrySet()) { System.out.println("Item : " +
		 * entry.getKey() + " " + entry.getValue()); }
		 */
		
		String jsonForWrite="{"+getJsonFromMap(configMap)+"}";
		
		File directory = new File("./PlayesProfile/");
	    if (! directory.exists()){
	        directory.mkdir();
	        // If you require it to make the entire directory path including parents,
	        // use directory.mkdirs(); here instead.
	    }
;
		String dir = "./PlayesProfile/";
        //String dir = WriteResource.class.getResource("/dir").getFile();
        OutputStream os=null;
		try {
			os = new FileOutputStream(dir + "/"+playerId+".json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        final PrintStream printStream = new PrintStream(os);
        printStream.println(jsonForWrite);
        printStream.close();
        
         
		return true;
	}

	public static Map<String, Object> readDataFromFile(String basePath, String filename,boolean defaultProfile) {
		InputStream resourceAsStream=null;
		InputStream InputStreamReader=null;
		BufferedReader bufferReader=null;
		FileReader fileReader = null;
		if (defaultProfile)
		{
			resourceAsStream = GameConfigurationPorcessor.class.getResourceAsStream("/" + basePath + "/" + filename);
			if (resourceAsStream==null) return null;  //there is no record for player before
			bufferReader = new BufferedReader(new InputStreamReader(resourceAsStream));
		}
		else
		{
			try {
				fileReader = new FileReader("./PlayesProfile/" + filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("./PlayesProfile/" + filename +" not found loading default profile");
				return  null;
			}
			bufferReader = new BufferedReader(fileReader);
			
		}
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = bufferReader) {
			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());
			// sb.append(list);

		} catch (IOException e) {
			
			e.printStackTrace();
			return null;  //there is no record for player before
		}

		list.forEach(new Consumer<String>() {
			public void accept(String name) {
				sb.append(name);
			}
		});
		// System.out.println(sb);
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

		try {
			engine.eval(EXTRACTOR_SCRIPT);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Invocable invocable = (Invocable) engine;
		JSObject result = null;
		try {
			result = (JSObject) invocable.invokeFunction("fun", sb);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> outputMap = (Map) convertIntoJavaObject(result);
		for (Map.Entry entry : outputMap.entrySet()) {
			System.out.println("Item : " + entry.getKey() + " " + entry.getValue());
		}

		// System.out.println(convertIntoJavaObject(result).getClass());
		return outputMap;
	}

	private static Object convertIntoJavaObject(Object scriptObj) {
		if (scriptObj instanceof ScriptObjectMirror) {
			ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
			if (scriptObjectMirror.isArray()) {
				List<Object> list = new ArrayList();
				for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
					list.add(convertIntoJavaObject(entry.getValue()));
				}
				return list;
			} else {
				Map<String, Object> map = new HashMap();
				for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
					map.put(entry.getKey(), convertIntoJavaObject(entry.getValue()));
				}
				return map;
			}
		} else {
			return scriptObj;
		}
	}

}
