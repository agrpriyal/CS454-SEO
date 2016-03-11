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
	
	
	
	public static void readCrawledJSON(String path) 
	{
		JSONParser parser = new JSONParser();
		Object object;
			try
			{
				object = parser.parse(new FileReader(path));
				JSONArray jsonArray = (JSONArray) object;

				for (Object o : jsonArray)
				{
					JSONObject jsonObject = (JSONObject) o;
					String url = jsonObject.get("url").toString();					
					
					if(!urlLinkList.contains(url))
					{
						System.out.println("Collecting links information from "+ url);
						pareAndCalculate(url);
						urlLinkList.add(url);
					}
					
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File not found");
			}
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void calculateDefaultRank()
	{
		int totalLinksCount = urlLinkList.size();
		double initialRank =1.0/totalLinksCount;
		for(String url: urlLinkList)
		{
			rankMap.put(url, initialRank);
		}
	}	
		
	
	private static void applyRanking()
	{	
		double rank;
		for(int i=0;i<10;i++)
		{
			for(String url: urlLinkList)
			{
				System.out.println("Currently ranking "+url);
				
				rank=0.0;
				if(inboundLinksMap.get(url)!=null)
				{
					
					List<String> incoming = inboundLinksMap.get(url);
					
					for(String link:incoming)
					{
						rank= rankMap.get(link);
						Double newRank = rankMap.get(link)/outboundLinksCountMap.get(link);
						rank=rank+newRank;
					}
					rankMap.put(url, rank);
				}				
									
			}
		}
	}

	public static void pareAndCalculate(String url1)
	{
		try
		{
			new HashMap<String, Object>();
			
			Tika tika = new Tika();
			tika.setMaxStringLength(10*1024*1024); 
			Metadata met=new Metadata();
			URL url = new URL(url1);
			
			ToHTMLContentHandler toHTMLHandler=new ToHTMLContentHandler();
			ParseContext parseContext=new ParseContext();
			LinkContentHandler linkHandler = new LinkContentHandler();
			ContentHandler textHandler = new BodyContentHandler(10*1024*1024);
			TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);		
			
			AutoDetectParser parser=new AutoDetectParser();
		    parser.parse(url.openStream(),teeHandler,met,parseContext);
		    
		    List<Link> links = linkHandler.getLinks();
		    
		    if(links.size()!=0)
		    {
		    	
		    	List<String> lists = new ArrayList<String>();
		    	outboundLinksCountMap.put(url1, links.size());
		    	
		    	for(Link l: links)
		    	{
		    		lists.add(l.getUri());
		    	}	
		    	
		    	outboundLinksMap.put(url1, lists);
		    }
		    
		    int count = 0;
			List<String> selfLinksLink = new ArrayList<String>();
			
			if(inboundLinksCountMap.containsKey(url))
				count=inboundLinksCountMap.get(url);
		
			
			for (Map.Entry<String, List<String>> entry : outboundLinksMap.entrySet()) {
				List<String> linksList = entry.getValue();			
				
				if(linksList.size()>0)
				{
				
				if(linksList.contains(url))
				{				
					selfLinksLink.add(entry.getKey());
					count++;
				}
				}
			}
			
			if(count>0)
			{
				inboundLinksMap.put(url1, selfLinksLink);
				
				if(inboundLinksCountMap.containsKey(url))
					inboundLinksCountMap.put(url1, inboundLinksCountMap.get(url)+1);
				else
					inboundLinksCountMap.put(url1, count);
			}
		    
		}
		catch (  MalformedURLException e1) {		    
		    System.out.println("Malformed URl");
		  }
		
		catch (Exception e){
			System.out.println("");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void saveAsJSON(File file1) {
		try
		{
			
			JSONArray jsonArrayToPrint = new JSONArray();
			
				
				for (Map.Entry<String, Double> entry : rankMap.entrySet()) 
				{
					JSONObject obj = new JSONObject();
					obj.put("url", entry.getKey());
					obj.put("value", entry.getValue());
					jsonArrayToPrint.add(obj);
				}
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			new Gson();
			String pretJson = prettyGson.toJson(jsonArrayToPrint);

			FileWriter file = new FileWriter(file1.getAbsolutePath(), true);
			file.write(pretJson.toString());
			file.write("\n\n");
			file.flush();
			file.close();

			System.out.println("Done writing into file");
		}
		catch (IOException e)
		{

		}

	}
}