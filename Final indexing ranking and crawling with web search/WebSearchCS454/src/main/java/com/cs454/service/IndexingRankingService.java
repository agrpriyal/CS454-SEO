package com.cs454.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cs454.domain.Page;

public class IndexingRankingService 
{
	public static List<Page> pages = new ArrayList<Page>();
	public static List<Page> tempList = new ArrayList<Page>();
	public static List<Page> tempFinalList = new ArrayList<Page>();
	public static final double TFID_WEIGHT = 0.7d;
	public static final double PAGE_RANK_WEIGHT = 0.3d;
	public static double tfidHighestValue;
	public static double pageRankHighestValue;
    
    public static List<Page> sort() 
    {
        List<Page> finalList = new ArrayList<Page>();
        List<Page> responseList = new ArrayList<Page>();
    	tempList = pages;
    	pages = null;
    	
    	sortBasedOnTfid(tempList);
    	
    	sortBasedOnWordCount(tempList);
    	
    	for (Page temp: tempList) {
    		System.out.println("tfidf =>"+temp.getTfid());
    	}
    	
    	
    	if ( tempList != null && tempList.size() > 0 ) {
    		tfidHighestValue = tempList.get(0).getTfid();
    	}
    	
    	for (Page temp: tempList) {
    		temp.setCalculatedTfid((temp.getTfid() / tfidHighestValue) * TFID_WEIGHT);
    	}
    	
    	sortBasedOnPrivateValue(tempList);
    	
    	finalList= sortBasedOnPageRankValue(tempFinalList,finalList);
    	
    	for (Page temp: finalList) {
    		System.out.println("page rank =>"+temp.getPageAnalysisValue());
    	}
    	
    	if ( finalList != null && finalList.size() > 0 ) {
    		pageRankHighestValue = finalList.get(0).getPageAnalysisValue();
    	}
    	
    	for (Page temp: finalList) {
    		temp.setCacculatedPageRank((temp.getPageAnalysisValue() / pageRankHighestValue) * PAGE_RANK_WEIGHT);
    	}
    	
    	for (Page temp: finalList) {
    		temp.setOverAllWeight( temp.getCalculatedTfid() + temp.getCacculatedPageRank() );
    	}
    	
    	responseList = sortBasedOnFinalOverallWeight(finalList,responseList);
    	
    	System.out.println(responseList.size());
    	int i=0;
    	for(Page p: responseList)
		{
    		/*System.out.println("Count==>"+i++);
    		System.out.println("url "+p.getUrl());
    		System.out.println("desc "+p.getDescription());
    		System.out.println("tfid "+p.getTfid());
    		System.out.println("title"+p.getTitle());
    		System.out.println("localpath "+p.getLocalPath());
    		System.out.println("count "+p.getCount());
    		System.out.println("author "+p.getAuthor());
    		System.out.println("pageAnalysis "+ p.getPageAnalysisValue());
    		System.out.println("private "+ p.getPrivateRankValue());
    		System.out.println("---------------------------------------------------");*/
    		
    		System.out.println("url"+p.getUrl());
    		System.out.println("tfid"+p.getTfid());
    		System.out.println("tfidHighestValue"+tfidHighestValue);
    		System.out.println("pageAnalysis"+p.getPageAnalysisValue());
    		System.out.println("pageRankHighestValue"+pageRankHighestValue);
    		System.out.println("calculated tfid"+p.getCalculatedTfid());
    		System.out.println("calculated page"+p.getCacculatedPageRank());
    		System.out.println("Over final weight"+p.getOverAllWeight());
		}
    	tempList = null;
    	tempFinalList = null;
    	finalList = null;
    	return responseList;
	}
    
    
    public static List<Page> sortBasedOnFinalOverallWeight(List<Page> tempList2,List<Page> finalList) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Page p1 = tempList2.get(j);
				Page p2 = tempList2.get(j+1);
				
				if(p1.getOverAllWeight()<p2.getOverAllWeight())
				{
					Page temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		
		return finalList;
	}
    


    public static List<Page> sortBasedOnPageRankValue(List<Page> tempList2,List<Page> finalList) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Page p1 = tempList2.get(j);
				Page p2 = tempList2.get(j+1);
				
				if(p1.getPageAnalysisValue()<p2.getPageAnalysisValue())
				{
					Page temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				finalList.add(tempList2.get(i));
			}
		}
		
		return finalList;
	}

    public static void sortBasedOnPrivateValue(List<Page> tempList2)
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Page p1 = tempList2.get(j);
				Page p2 = tempList2.get(j+1);
				
				if(p1.getPrivateRankValue()<p2.getPrivateRankValue())
				{
					Page temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
		
	}

    public static void sortBasedOnWordCount(List<Page> tempList2) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Page p1 = tempList2.get(j);
				Page p2 = tempList2.get(j+1);
				
				if(p1.getCount()<p2.getCount())
				{
					Page temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
		
		
	}

    public static void sortBasedOnTfid(List<Page> tempList2) 
	{
		for(int i=0;i<tempList2.size();i++)
		{
			for(int j=0;j<tempList2.size()-1;j++)
			{
				Page p1 = tempList2.get(j);
				Page p2 = tempList2.get(j+1);
				
				if(p1.getTfid()<p2.getTfid())
				{
					Page temp = p1;
					p1=p2;				
					tempList2.set(j, p1);
					p2=temp;
					tempList2.set(j+1, p2);
				}
			}		
		}
		
		if(tempList!=null)
			
		
		if(tempList2.size()<10)
		{
			for(int i=0;i<tempList2.size();i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}
		else
		{
			for(int i=0;i<10;i++)
			{			
				if(!tempFinalList.contains(tempList2.get(i)))
					tempFinalList.add(tempList2.get(i));
			}
		}		
	}

    public static void rankReader(String rankPath) 
    {
    		JSONParser parser = new JSONParser();
    		Object object;    		

    		try
    		{
    			object = parser.parse(new FileReader(rankPath));
    			JSONArray jsonArray = (JSONArray) object;   			
    			
    			System.out.println(jsonArray.size());
    			
    			
    			for(Object o : jsonArray)
    			{    				
    				JSONObject jsonObj = (JSONObject) o;
    				String url = jsonObj.get("url").toString();
    				Double value =Double.parseDouble(jsonObj.get("value").toString());
    				
    				for(Page p: pages)
    				{
    					if(p.getUrl().equals(url))
    					{
    						p.setPageAnalysisValue(value);
    						break;
    					}
    				}
    							
    			}
    		}
    		catch(Exception e)
    		{
    			System.out.println("Error occured");
    		}
		
	}

    public static void print()
    {
    	for(Page p: pages)
    	{
    		System.out.println("url "+p.getUrl());
    		System.out.println("desc "+p.getDescription());
    		System.out.println("tfid "+p.getTfid());
    		System.out.println("title"+p.getTitle());
    		System.out.println("localpath "+p.getLocalPath());
    		System.out.println("count "+p.getCount());
    		System.out.println("author "+p.getAuthor());
    		System.out.println("pageAnalysis "+ p.getPageAnalysisValue());
    		System.out.println("private "+ p.getPrivateRankValue());
    		System.out.println("---------------------------------------------------");
    	}
    }
    
    public static void readIndexer(String indexPath,String word)
    {
    	pages = new ArrayList<Page>();
    	tempFinalList = new ArrayList<Page>();
    	tempList = new ArrayList<Page>();
    	
    	JSONParser parser = new JSONParser();
		Object object;	
		JSONArray innerJsonArray = new JSONArray();

		try
		{
			object = parser.parse(new FileReader(indexPath));
			JSONArray jsonArray = (JSONArray) object;

			System.out.println(jsonArray.size());
			for (Object o : jsonArray)
			{
				
				
				JSONObject jsonObject = (JSONObject) o;
				innerJsonArray = new JSONArray();
				innerJsonArray= (JSONArray)jsonObject.get(word);				
			}
			
			if ( innerJsonArray!= null)
			{
			
			for(Object innerObject:innerJsonArray)
			{
				Page page = new Page();
				
				JSONObject obj = (JSONObject) innerObject;				
				int count =Integer.parseInt(obj.get("Count").toString());
				String url = obj.get("url").toString();
				double tfid =Double.parseDouble(obj.get("tfid").toString());
				String fileName = obj.get("localFileName").toString();
				String title = obj.get("title").toString();
				String author = obj.get("author").toString();
				String description = obj.get("description").toString();
				
				page.setCount(count);
				page.setLocalPath(fileName);
				page.setUrl(url);
				page.setTfid(tfid);
				page.setTitle(title);
				page.setAuthor(author);
				page.setDescription(description);
				
				pages.add(page);
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
			
			e.printStackTrace();
		}
    	
	}

	public static void privateRank(String word)
    {
    	for(Page p: pages)
    	{
    		p.setPrivateRankValue(1);
    	}
    	
    	for(Page p: pages)
    	{
    		if(p.getTitle().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1);
    		
    		if(p.getDescription().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1);
    		
    		if(p.getAuthor().toLowerCase().contains(word.toLowerCase()))
    			p.setPrivateRankValue(p.getPrivateRankValue()+1); 		
    		
    	}
    			
    	
    }
    
    
}
