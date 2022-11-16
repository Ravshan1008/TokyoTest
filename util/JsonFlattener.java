package com.clearskye.test.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * 
 * @author Reza
 */

public class JsonFlattener {
	public static HashMap<String, Object> getMapFromDoc(String content) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> userInMap = null;
		try {
			userInMap = mapper.readValue(content, new TypeReference<Map<String, Object>>() {
			});
		} catch (JsonParseException e) {
			throw new Exception(e.getMessage());
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}

		return (hashMapMerg(userInMap));
	}

	private static HashMap<String, Object> hashMapMerg(HashMap<String, Object> map) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		Set<String> set = map.keySet();
		for (Iterator<String> i = set.iterator(); i.hasNext();) {
			String key = i.next();
			Object value = map.get(key);
			if (value instanceof HashMap) {
				HashMap<String, Object> map1 = (HashMap<String, Object>) value;
				Set<String> set1 = map1.keySet();
				for (Iterator<String> i1 = set1.iterator(); i1.hasNext();) {
					String key1 = i1.next();
					Object value1 = map1.get(key1);
					result.put(key + "." + key1, value1);
				}
			} else {
				if (value instanceof String)
					result.put(key, value);
				else
					result.put(key, value);
			}
		}

		boolean anyHashmaps = false;
		Set<String> set3 = result.keySet();
		for (Iterator<String> i3 = set3.iterator(); i3.hasNext();) {
			String key3 = i3.next();
			Object value = result.get(key3);
			if (value instanceof HashMap) {
				anyHashmaps = true;
			}
		}

		if (anyHashmaps)
			result = hashMapMerg(result);

		return result;

	}

	public static String getDocFromHashMap(HashMap<String, Object> map) {
		String result = "{";
		Set<String> set = map.keySet();
		for (Iterator<String> i = set.iterator(); i.hasNext();) {
			String key = i.next();
			Object value = map.get(key);
			if (value != null) {
				if (value instanceof String)
					result += "\"" + key + "\"" + ":" + "\"" + value.toString() + "\"";
				else
					result += "\"" + key + "\"" + ":" + value.toString();
			} else {
				result += "\"" + key + "\"" + ":" + null;
			}

			if (i.hasNext())
				result += ",";
		}
		result += "}";
		return result;
	}

}
