package com.cs454.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs454.domain.Page;
import com.cs454.service.IndexingRankingService;

 
@Controller
public class SearchController {
 
	@SuppressWarnings("unused")
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SearchController.class);
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "Search";
	}
	
	@RequestMapping(value = "/search.html", method = RequestMethod.POST)
	public String searchWords(@RequestParam String word, ModelMap model) {
    	
    	String indexPath = "C:\\crawler25\\data\\Crawler\\indexing.json";    	
    	String rankPath = "C:\\crawler25\\data\\Crawler\\ranking.json"; 
    	
    	IndexingRankingService.readIndexer(indexPath,word);

    	IndexingRankingService.rankReader(rankPath);

    	IndexingRankingService.privateRank(word);

    	List<Page> finalList = new ArrayList<Page>();

		finalList =  IndexingRankingService.sort();		
		
		model.put("finalList", finalList);
		finalList = null;
		return "results"; 
	}
	
	/*@RequestMapping(value = "/searchResult.html", method = RequestMethod.POST)
	public String welcomeName(@RequestParam String word, ModelMap model) throws IOException, org.apache.lucene.queryparser.classic.ParseException
	{
		System.out.println("word==>"+word);
		
		String path = null;
		
		List<String> finalList = new ArrayList<String>();
		Map<String,Double> resultURLScore = new HashMap<String,Double>();
		Map<String,Double> tempMap = new HashMap<String,Double>();
		
			String indexLocation = "C://Users//prajith//Desktop//desktopdoc//index";
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
			Directory directory = FSDirectory.open(new File(indexLocation));
			IndexReader reader = IndexReader.open(directory);
			
			IndexSearcher searcher = new IndexSearcher(reader);
			TopScoreDocCollector collector = TopScoreDocCollector.create(20, true);
			
			
				QueryParser parser = new QueryParser(Version.LUCENE_35, "contents",
						analyzer);
				Query q = parser.parse(word);
			
				searcher.search(q, collector);
				ScoreDoc[] hits = collector.topDocs().scoreDocs;

				
				
				for (int i = 0; i < hits.length; ++i) {
					int docId = hits[i].doc;
					Document d = searcher.doc(docId);
					
					
					System.out.println((i + 1) + ". " + d.get("path") + " score=" + hits[i].score);
					if ( d.get("path") != null ){
					path = d.get("path").replace("\\", "/");
					
					Double idf = (double) Math.log10((double) reader.maxDoc() / (double) hits[i].score) ;
					
					resultURLScore.put(path,  idf * hits[i].score  );
					}
					finalList.add(d.get("path"));
					
				}
				hits = null;
				
				JSONParser parser1 = new JSONParser();
				Object object;

				try
				{
					object = parser1.parse(new FileReader("C://Users//prajith//Desktop//desktopdoc//Ranking//Ranking.json"));
					JSONArray jsonArray = (JSONArray) object;

					System.out.println(jsonArray.size());
					
					for (Object o : jsonArray)
					{
						JSONObject jsonObject = (JSONObject) o;
						for ( Map.Entry<String, Double> result : resultURLScore.entrySet())
						{
							if ( jsonObject.get(result.getKey()) != null) {
								System.out.println("jsonObject==>"+jsonObject);
								System.out.println(Double.parseDouble(new DecimalFormat("##.###").format(jsonObject.get(result.getKey()))));
								System.out.println(Double.parseDouble(new DecimalFormat("##.###").format(result.getValue())));
								Double newscore = (Double)jsonObject.get(result.getKey()) + result.getValue();
								newscore = Double.parseDouble(new DecimalFormat("##.###").format(newscore));
								tempMap.put(result.getKey(), newscore );
								System.out.println("New Score==>"+newscore);
							}
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
				
				tempMap = sortByValues(tempMap);
				System.out.println();
				for(String s : tempMap.keySet())	
					System.out.println(s +" - "+ tempMap.get(s));
				
				model.put("finalList", tempMap);
				return "results"; 	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Double> sortByValues(Map<String, Double> map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}

			public Comparator reversed() {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparing(Comparator other) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparing(Function keyExtractor,
					Comparator keyComparator) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparing(Function keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparingInt(ToIntFunction keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparingLong(ToLongFunction keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
				// TODO Auto-generated method stub
				return null;
			}

			public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
				// TODO Auto-generated method stub
				return null;
			}

			public <T> Comparator<T> nullsFirst(
					Comparator<? super T> comparator) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T> Comparator<T> nullsLast(
					Comparator<? super T> comparator) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T, U> Comparator<T> comparing(
					Function<? super T, ? extends U> keyExtractor,
					Comparator<? super U> keyComparator) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T, U extends Comparable<? super U>> Comparator<T> compa1ring(
					Function<? super T, ? extends U> keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T> Comparator<T> comparingInt(
					ToIntFunction<? super T> keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T> Comparator<T> comparingLong(
					ToLongFunction<? super T> keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}

			public <T> Comparator<T> comparingDouble(
					ToDoubleFunction<? super T> keyExtractor) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}*/
 
}
