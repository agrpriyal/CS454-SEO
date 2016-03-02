package cs454.webCrawler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		
		//in roder to test link analysis, you need to supply the mapping of document name with the document's URL. (WebAdress)
		//program will receive an error if the size of the urlMap doesn't match the number of documents in the directory folder.
		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put("uuid1.html", "http://www.pandora.com");
		urlMap.put("uuid2.html", "http://www.pandora.com/about");
		urlMap.put("uuid3.html", "http://www.pandora.com/one/gift");
		urlMap.put("uuid4.html", "http://www.pandora.com/legal");
		
		LinkAnalysis la = new LinkAnalysis("C:/Users/Volfurious/Desktop/linktest", (HashMap<String, String>) urlMap);
		System.out.println("Original result" + " " + la.getScores().toString());
		System.out.println("Normalized result" + " " + la.getNormalizedScores().toString());
		//System.out.println(la.getInMap().toString());
		//System.out.println(la.getOutMap().toString());
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
