package com.piinalpin.websocketserver.handler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/
public class SearchAlgorithm {

	static Root example = null;
	static Map<String,String> keyValues = new HashMap<String,String>();
	static List<String> keyList = new ArrayList<String>();
	static 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			 example = objectMapper.readValue(new File("intents.json"), Root.class);
			List<Intent> list =example.intents;
			
			for(Intent intent : list)
			{
				for(int i=0;i<intent.patterns.size();i++) {
				keyValues.put(intent.patterns.get(i),intent.responses.get(i));
				keyList.add(intent.patterns.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * JSONParser parser = new JSONParser();
		 * 
		 * try { Object obj = parser.parse(new FileReader("c:\\file.json"));
		 * 
		 * JSONObject jsonObject = (JSONObject) obj;
		 * 
		 * String name = (String) jsonObject.get("name"); System.out.println(name);
		 * 
		 * String city = (String) jsonObject.get("city"); System.out.println(city);
		 * 
		 * String job = (String) jsonObject.get("job"); System.out.println(job);
		 * 
		 * // loop array JSONArray cars = (JSONArray) jsonObject.get("cars");
		 * Iterator<String> iterator = cars.iterator(); while (iterator.hasNext()) {
		 * System.out.println(iterator.next()); } } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 */
		
	}
	public String test(String key)
	{
		if(keyList.contains(key))
		{
			return keyValues.get(key);
		}
		return null;
	}
}
