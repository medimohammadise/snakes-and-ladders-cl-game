package my.practice.codechallenges.puzzle.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.tools.JavaFileObject;
import jdk.nashorn.api.scripting.*;


import my.practice.codechallenges.puzzle.manager.AsciiArtManager;

public class GameConfigurationPorcessor {
	  private static final String EXTRACTOR_SCRIPT =
			    "var fun = function(raw) { " +
			    "var json = JSON.parse(raw); " +
			    "return json;}";

	  public static void main(String args[]) {
		  readDataFromFile("configuration","inputConfigFile.json");
		}
	  public static Map<String,Object> readDataFromFile(String basePath,String filename) {
		
		  InputStream resourceAsStream = AsciiArtManager.class.getResourceAsStream("/"+basePath+"/"+filename);
		  BufferedReader bufferReader = new BufferedReader(new InputStreamReader(resourceAsStream));
			List<String> list = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = bufferReader) {
				//br returns as stream and convert it into a List
				list = br.lines().collect(Collectors.toList());
				//sb.append(list);

			} catch (IOException e) {
				e.printStackTrace();
			}

			list.forEach(new Consumer<String>() {
			    public void accept(String name) {
			        sb.append(name);
			    }
			});
            System.out.println(sb);
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			
				
			try {
				engine.eval(EXTRACTOR_SCRIPT);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Invocable invocable = (Invocable) engine;
			JSObject result=null;
			try {
				result = (JSObject) invocable.invokeFunction("fun",sb );
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ScriptException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Map<String,Object> outputMap=(Map)convertIntoJavaObject(result);
			for (Map.Entry entry :outputMap.entrySet()) {
				System.out.println("Item : " + entry.getKey() +" " + entry.getValue());
			}
			
			//System.out.println(convertIntoJavaObject(result).getClass());
			return outputMap;
	  }
	  private static Object convertIntoJavaObject(Object scriptObj) {
		    if (scriptObj instanceof ScriptObjectMirror) {
		        ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
		        if (scriptObjectMirror.isArray()) {
		            List<Object> list =new ArrayList();
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
