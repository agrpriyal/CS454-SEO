package cs454.webCrawler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class WebCrawlerMain {

	public static void main( String[] args ) throws FileNotFoundException, IOException
    {
    	if(args[0]!=null){
    		try
    		{
    			
    			new WebCrawler().crawl(args[0]);
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	CrawlerFileOperation.saveContent(WebCrawler.file1);
    	Map<String, String> urlMap = WebCrawler.urlMap;
    	Properties properties = new Properties();

		for (Map.Entry<String,String> entry : urlMap.entrySet()) {
		    properties.put(entry.getKey(), entry.getValue());
		}

		properties.store(new FileOutputStream("map.properties"), null);
		System.out.println(urlMap.size());
    }

}
