package cs454.indexing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.tika.sax.BodyContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cs454.indexing.CommonUtils.metaDataType;

public class IndexingMain {
	
	private static List<String> stopWordList = Arrays.asList("a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hasn't", "hadn't", "has", "have", "haven't", "having", "he", "hed", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves", "out", "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they", "they'd", "they're", "they'll", "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's", "whom", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves");
	static Map<String,List<Words>> wordMap = new HashMap<String, List<Words>>();
	public static void main(String args[]) throws IOException {
		
		
		try
		{
			if ( args.length > 1 && args[0] != null && args[1] != null && args[2] != null)
			{
				readCrawlerJSON(args[0]);
				IndexingFileOperation.saveAsJSON(wordMap, new File(args[1]));
				new Ranking().computeRanking(args[0], args[2]);
			}else
			{
				System.out.println("Invalid Parameter! Usage: Indexing <crawled_JSON_Path> <index_JSON_Path> <ranking_JSON_Path>");
				System.exit(0);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void readCrawlerJSON(String path) throws NoSuchElementException
    {
		JSONParser parser = new JSONParser();
		Object object;	

		try
		{
			object = parser.parse(new FileReader(path));
			JSONArray jsonArray = (JSONArray) object;

			System.out.println(jsonArray.size());
			
			System.out.println("Start indexing on Crawled website");
			
			for (Object o : jsonArray)
			{
				
				JSONObject jsonObject = (JSONObject) o;
				String localpath = jsonObject.get("localpath").toString();
				String url =  jsonObject.get("url").toString();
				
				JSONObject jsonObject1 =(JSONObject) jsonObject.get("met");
				JSONObject jsonObject2 =(JSONObject) jsonObject1.get("metadata");
				
				
				Set<String> key = jsonObject2.keySet();
				
				for (metaDataType meta : CommonUtils.metaDataType.values() )
				{
					
				if(key.contains(meta.toString()))
				{
					String title="";
					String author="";
					String value = jsonObject2.get(meta.toString()).toString() ;
					String innerMetadata = jsonObject2.get(meta.toString()).toString() ;
					
					StringTokenizer stringTokenizer = new StringTokenizer(
							innerMetadata.toString().replaceAll("\\s+", " ")," .,-");
					
					while (stringTokenizer.hasMoreTokens()) {
						String element = stringTokenizer.nextToken();
						element = processWord(element);
						if (!stopWordList.equals((element)) && checkElements(element) == false && element.length()>2 && isNumeric(element) == false) {
							indexing(localpath,url,element,meta.toString(),value);
						}
					}
				}
				}
				
				System.out.println("Currently processing the file "+localpath);
				
				BodyContentHandler bodyHandler = IndexingFileOperation.readDownloadedFile(localpath);
				
				if(bodyHandler!=null)
				{
					
					StringTokenizer stringTokenizer = new StringTokenizer(
							bodyHandler.toString().replaceAll("\\s+", " ")," .,-");
					
					while (stringTokenizer.hasMoreTokens()) {
						String element = stringTokenizer.nextToken();
						element = processWord(element);

						if (!stopWordList.equals((element)) && checkElements(element) == false && element.length()>2 && isNumeric(element) == false) {
							indexing(localpath,url,element,"","");
						}
					}
					
					
		        }
				
			}
		}catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			
			e.printStackTrace();
		}
	}
	
	
	
private static void indexing(String localpath, String url,String token,String meta,String value) {
		
        		        		
        	if(wordMap.get(token)==null)
        	{
        		List<Words> wordsList = new ArrayList<Words>();
        		Words word = new Words();   			    			       			
    			
        		word.setWord(token);
        		word.setWordCount(1); 
        		word.setLocalFileName(localpath);
        		word.setUrl(url);
        		word.setAuthor("Author" == meta ? value : "");
        		word.setDesc( "Description" == meta ? value : ( "description" == meta ?  value : "" ) );
        		word.setTitle("title" == meta ? value : "");
        		wordsList.add(word);
    			wordMap.put(token, wordsList);
        	}
        	else
        	{
        		List<Words> wordsList = wordMap.get(token);
        		int flag=0;
        		for(Words w : wordsList)
            	{		        		
            		if(w.getWord().equals(token) && w.getLocalFileName().equals(localpath))
            		{
            			w.setWordCount(w.getWordCount()+1);
            			flag=1;
            			break;
            		}
            	}
        		
        		if(flag==0)
        		{
        			Words word = new Words();   			    			       			
        			
        			word.setWord(token);
        			word.setWordCount(1); 
        			word.setLocalFileName(localpath);
        			word.setUrl(url);
        			word.setAuthor("Author" == meta ? value : "");
            		word.setDesc( "Description" == meta ? value : ( "description" == meta ?  value : "" ) );
            		word.setTitle("title" == meta ? value : "");
        			
        			wordsList.add(word);
        		}
        		wordMap.put(token, wordsList);        		
        	}
	}
	
	public static boolean checkElements(String element)
	{
		if(element.equals("#") || element.equals("&") || element.equals("+") || element.equals("-") || element.equals("") || element.equals("|") || element.equals(".") || element.equals("\\\\"))
			return true;
		
		return false;
	}
	
	public static boolean isNumeric(String element)
	{
		String regex = "[0-9]+";
		if(element.matches(regex))
			return true;
		
		return false;
	}
	
	private static String processWord(String x) {
	    return x.replaceAll("[\\]\\[(){}\\*:,.;!?<>%]", "");
	}
	
}
