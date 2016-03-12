package cs454.webCrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.tika.exception.TikaException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ScoreMerger {
static JSONArray jsonArray = new JSONArray();
	
	@SuppressWarnings("unchecked")
	public static void main( String[] args ) throws IOException, SAXException, TikaException, ParseException, JSONException
    {
		File file = new File("C:/Users/Volfurious/Desktop/linktest");//folder path
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

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put("uuid1.html", "http://www.pandora.com");
		urlMap.put("uuid2.html", "http://www.pandora.com/about");
		urlMap.put("uuid3.html", "http://www.pandora.com/one/gift");
		urlMap.put("uuid4.html", "http://www.pandora.com/legal");
		
		LinkAnalysis la = new LinkAnalysis("C:/Users/Volfurious/Desktop/linktest", (HashMap<String, String>) urlMap);
		JSONObject linkJson = la.getJson();
		
		JSONObject merged = new JSONObject();
		merged.put("weight", objTemp);
		merged.put("link", linkJson);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String pretJson = prettyGson.toJson(merged);
		String path = "C:/Users/Volfurious/Desktop/json/score json.txt";
		try(FileWriter outputJson = new FileWriter(path, false)){
			outputJson.write(pretJson.toString());
			outputJson.write("\n\n");
			outputJson.flush();
			outputJson.close();
		}

		Properties properties = new Properties();

		for (Map.Entry<String,String> entry : urlMap.entrySet()) {
		    properties.put(entry.getKey(), entry.getValue());
		}

		properties.store(new FileOutputStream("map.properties"), null);
    }
}
