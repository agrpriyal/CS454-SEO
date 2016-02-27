package cs454.webCrawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

public class WeightCalculator {

	private int numberOfDocuments = 0;
	private JSONObject documents;
	private Set<String> query = new HashSet<String>();
	
	public WeightCalculator(String queryIn, JSONObject JObj, int numberOfDocuments){
		this.numberOfDocuments = numberOfDocuments;
		this.documents = JObj;
		query.addAll(Arrays.asList(queryIn.toLowerCase().split(" ")));
		
		for (String keyword: query){
			calcScores(documents, keyword);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void calcScores(JSONObject documentsIn, String keyword){
		//double score = 0;
		double occurencesInDocuments = 0;
		List<JSONObject> documentsContainWord = new ArrayList<JSONObject>();
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = documentsIn.keySet().iterator(); iterator.hasNext();){
			String key = (String) iterator.next();
			JSONObject documentWords = (JSONObject) documentsIn.get(key);
			if(documentWords.containsKey(keyword)){
				occurencesInDocuments++;
				documentWords.put("document name", key.toString());
				documentsContainWord.add(documentWords);
			}
		}
		for (JSONObject currentObj: documentsContainWord){
			System.out.println(currentObj.get("document name") + " " + keyword + ": " + calculateWeight(currentObj, keyword, occurencesInDocuments));
			//prints the weight of each word in the documents it appeared in
			//score += calculateWeight(currentObj, keyword, occurencesInDocuments);
		}
		//System.out.println(keyword + ": " + score);
	}
	
	public double calculateWeight(JSONObject currentDoc, String word, double df){
		double idf = Math.log10(numberOfDocuments/df);
		JSONObject keywordJsonObj = (JSONObject) currentDoc.get(word);
		int frequency = (int) keywordJsonObj.get("frequency");
		double tf = Math.log(1 + frequency);
		double weight = tf * idf;
		return weight;
	}
	
	public Set<String> getQuery(){
		return query;
	}
}
