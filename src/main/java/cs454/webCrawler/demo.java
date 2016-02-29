package cs454.webCrawler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

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
		
		WeightCalculator wc = new WeightCalculator(finalObj, fileCounter);
		JSONObject objTemp = wc.getNormalizedJson();
		System.out.println(objTemp);
		
		
		LinkAnalysis la = new LinkAnalysis("C:/Users/Volfurious/Desktop/testfiles");
		
		/*
		 * 
		 * NOTE: BUG
		 * if wc.getNormalizedJson is called after wc.getJson, the json object called from wc.getJson will be normalized!!
		 * not sure why this is happening, unable to fix it.
		 * if regular json file is needed, only call the method wc.getJson, do not call wc.getNormalizedJson before or after under any circumstances.
		System.out.println(objTemp.get("Test3.html"));
		JSONObject objTemp2 = wc.getNormalizedJson();
		System.out.println(objTemp.get("Test3.html"));
		
		//System.out.println(objTemp2.get("Test3.html"));
		//System.out.println(wc.getMax());
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String pretJson = prettyGson.toJson(word.getJson());
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(pretJson);
		JSONObject testObj = (JSONObject) obj;
		*/
    }
}
