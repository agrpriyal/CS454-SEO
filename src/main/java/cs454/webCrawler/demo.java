package cs454.webCrawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class demo {
	static JSONArray jsonArray = new JSONArray();
	
	public static void main( String[] args ) throws IOException, SAXException, TikaException, ParseException, JSONException
    {
		File file = new File("C:/Users/Volfurious/Desktop/test.html");//file path 
		WordCounter word = new WordCounter(file);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String pretJson = prettyGson.toJson(word.getJson());
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(pretJson);
		JSONObject testObj = (JSONObject) obj;

		for (@SuppressWarnings("rawtypes")
		Iterator iterator = testObj.keySet().iterator(); iterator.hasNext();){
			String key = (String) iterator.next();
			JSONObject innerObj = (JSONObject) testObj.get(key);
			
			JSONArray pos = (JSONArray) innerObj.get("position");
			@SuppressWarnings("unchecked")
			Iterator<String> numIterator = pos.iterator();
			List<Integer> tempList = new ArrayList<Integer>();//this is the list that contains all the position of the word
			while (numIterator.hasNext()){
				tempList.add(Integer.parseInt(numIterator.next()));
			}
			System.out.println(innerObj.get("word") + ": " + innerObj.get("frequency") + " " + tempList.toString());
		}
    }
}
