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
import java.nio.file.Files;
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
import my.practice.codechallenges.puzzle.domain.Player;
import my.practice.codechallenges.puzzle.manager.GameStateManager;

import static my.practice.codechallenges.puzzle.setting.Constants.PLAYER_CONFIG_DIR;
import static my.practice.codechallenges.puzzle.setting.Constants.INIT_CONFIG_FILE;
import static my.practice.codechallenges.puzzle.setting.Constants.PLAYER_PROFILE_DIR;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_STAR;

/*
 * This class is the hardest part I had to do that.Imagine that there is no GSON or ObjectMapper. But the nice part is that I could do that finally
 */
public class GameConfigurationPorcessor {
	// script for evaluating json file
	private static final String EXTRACTOR_SCRIPT = "var fun = function(raw) { " + "var json = JSON.parse(raw); "
			+ "return json;}";

	/*
	 * main function for converting state map to json
	 */
	private static String generateJsonFromMap(Map<String, Object> map) {

		StringBuilder stringBuilder = new StringBuilder();
		boolean multipleValue = false;
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof Map<?, ?>) {

				multipleValue = true;
				value = generateJsonFromMap((Map<String, Object>) value);

			} else if (value instanceof ArrayList) {
				ArrayList valueList = ((ArrayList) value);
				multipleValue = true;
				for (int i = 0; i < valueList.size(); i++) {

					if (valueList.get(i) instanceof Map<?, ?>) {
						value = generateJsonFromMap((Map<String, Object>) valueList.get(i));
					}

				}

			}

			if (stringBuilder.length() > 0)
				stringBuilder.append(",");
			if (!"".equals(value) && !multipleValue)
				stringBuilder.append("\"" + key + "\"" + ":" + "\"" + value + "\"");
			else {

				stringBuilder.append("\"" + key + "\"" + ":" + "{" + value + "}");
				multipleValue = false;
			}
		}

		return stringBuilder.toString();

	}

	public static boolean saveStateIntoJson(String basePath, String filename, Map<String, Object> configMap,
			String playerId) {

		String jsonForWrite = "{" + generateJsonFromMap(configMap) + "}";

		File directory = new File(PLAYER_PROFILE_DIR);
		if (!directory.exists()) {
			directory.mkdir();
		}

		OutputStream os = null;
		try {
			os = new FileOutputStream(PLAYER_PROFILE_DIR + "/" + playerId + ".json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final PrintStream printStream = new PrintStream(os);
		printStream.println(jsonForWrite);
		printStream.close();

		return true;
	}

	/*
	 * load data from game initial configuration data from json file
	 */
	public static Map<String, Object> readDataFromFile(String filename, boolean defaultProfile) {
		InputStream resourceAsStream = null;
		BufferedReader bufferReader = null;
		FileReader fileReader = null;
		if (defaultProfile) {
			resourceAsStream = GameConfigurationPorcessor.class
					.getResourceAsStream("/" + PLAYER_CONFIG_DIR + "/" + INIT_CONFIG_FILE);
			if (resourceAsStream == null)
				return null; // there is no record for player before
			bufferReader = new BufferedReader(new InputStreamReader(resourceAsStream));
		} else {
			try {
				fileReader = new FileReader(PLAYER_PROFILE_DIR + filename + ".json");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("./PlayesProfile/" + filename + ".json not found loading default profile");
				return null;
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
			return null; // there is no record for player before
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
			e1.printStackTrace();
		} catch (ScriptException e1) {
			e1.printStackTrace();
		}
		Map<String, Object> outputMap = (Map) convertIntoJavaObject(result);
		return outputMap;
	}

	// this method just for converting java script type into Java Map
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

	public static List<Player> getWinnersList() {
		Player winner = null;
		List<Player> winners = new ArrayList<Player>();
		File directory = new File(PLAYER_PROFILE_DIR);
		for (final File fileEntry : directory.listFiles()) {
			Map<String, Object> configMap = readDataFromFile(fileEntry.getName().substring(0, fileEntry.getName().length()-5), false);
			if (configMap!=null) winner = GameStateManager.getPlayerInfo(configMap);
			if (winner!=null && winner.getStar() > 0)
				winners.add(winner);

		}

		return winners;
	}

}
