package cs454.webCrawler;

import java.io.File;
import java.io.FilenameFilter;
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
	
	@SuppressWarnings("unchecked")
	public static void main( String[] args ) throws IOException, SAXException, TikaException, ParseException, JSONException
    {
		File file = new File("C:/Users/Volfurious/Desktop/testfiles");//folder path
		File[] htmlFiles = file.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				return name.endsWith("html");
			}
		});
		
		int fileCounter = 0;
		JSONObject finalObj = new JSONObject();
		for (File f: htmlFiles){
			WordCounter word = new WordCounter(f);
			finalObj.put(f.getName(), word.getJson());
			fileCounter++;
		}
		
		WeightCalculator wc = new WeightCalculator("name top hot account", finalObj, fileCounter);
		//System.out.println(wc.getQuery().toString());
		//WordCounter word = new WordCounter(file);
		
		/*
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String pretJson = prettyGson.toJson(word.getJson());
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(pretJson);
		JSONObject testObj = (JSONObject) obj;
		*/
    }
}
