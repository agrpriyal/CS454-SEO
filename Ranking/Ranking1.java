package cs454.indexing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.ContentHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ranking
{
	static List<String> urlLinkList = new ArrayList<String>();
	static Map<String,Integer> outboundLinksCountMap = new HashMap<String, Integer>();
	static Map<String,List<String>> outboundLinksMap = new HashMap<String, List<String>>();
	static Map<String,Integer> inboundLinksCountMap = new HashMap<String, Integer>();
	static Map<String,List<String>> inboundLinksMap = new HashMap<String, List<String>>();
	static Map<String,Double> rankMap = new HashMap<String, Double>();
	
	
	
	public void computeRanking(String crawledJSON,String rankingFileName)
	{
		readCrawledJSON(crawledJSON);
		
		System.out.println("Ranking process started");
		calculateDefaultRank();
		applyRanking();
		saveAsJSON(new File(rankingFileName));
		
		for (Map.Entry<String, Double> entry : rankMap.entrySet()) {
				System.out.println("url: " + entry.getKey());
				System.out.println("rank: " + entry.getValue());
				System.out.println("");
		}
	}
	
	
	