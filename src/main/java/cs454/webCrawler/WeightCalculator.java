package cs454.webCrawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

public class WeightCalculator {

	private int numberOfDocuments = 0;
	private JSONObject documents;
	private Set<String> setList = new HashSet<String>();
	private List<String> listOfUniqueWords = new ArrayList<String>();
	private JSONObject JsonScores = new JSONObject();
	private List<JSONObject> jsonList = new ArrayList<JSONObject>();
	private List<JSONObject> normalizedJsonList = new ArrayList<JSONObject>();
	
	public WeightCalculator(JSONObject JObj, int numberOfDocuments){
		this.numberOfDocuments = numberOfDocuments;
		this.documents = JObj;
		listUniqueWords();
		
		for (String keyword: listOfUniqueWords){
			calcScores(documents, keyword);
		}
		
	}
	
	public void listUniqueWords(){
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = documents.keySet().iterator(); iterator.hasNext();){
			String key = (String) iterator.next();
			JSONObject documentWords = (JSONObject) documents.get(key);
			for (@SuppressWarnings("rawtypes")
			Iterator iterator2 = documentWords.keySet().iterator(); iterator2.hasNext();){
				String key2 = (String) iterator2.next();	
				setList.add(key2);
			}
		}
		listOfUniqueWords.addAll(setList);
		java.util.Collections.sort(listOfUniqueWords);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void calcScores(JSONObject documentsIn, String keyword){
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
			JSONObject wordInfo = new JSONObject();
			wordInfo.put("word", keyword);
			wordInfo.put("tf-idf", calculateWeight(currentObj, keyword, occurencesInDocuments));
			wordInfo.put("document", currentObj.get("document name"));
			jsonList.add(wordInfo);
			//System.out.println(currentObj.get("document name") + " " + keyword + ": " + calculateWeight(currentObj, keyword, occurencesInDocuments));
		}
	}
	
	public double calculateWeight(JSONObject currentDoc, String word, double df){
		double idf = Math.log10(numberOfDocuments/df);
		JSONObject keywordJsonObj = (JSONObject) currentDoc.get(word);
		int frequency = (int) keywordJsonObj.get("frequency");
		double tf = Math.log(1 + frequency);
		double weight = tf * idf;
		return weight;
	}
	
	public double getMax(){
		double max = 0;
		for (JSONObject obj: jsonList){
			double value = (double) obj.get("tf-idf");
			if (value > max){
				max = value;
			}
		}
		return max;
	}
	
	//this stores all the tf-idf scores of words in the document they belong to. The final object JsonScores will be the main output which we will later use.
	//depending on user's preference, the values can be normalized or not.
	@SuppressWarnings("unchecked")
	public void jsonCreator(boolean normalize){
		
		List<JSONObject> listOfScores = new ArrayList<JSONObject>();
		if (normalize){
			listOfScores.addAll(normalizedJsonList);
		}
		else{
			listOfScores.addAll(jsonList);
		}
		
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = documents.keySet().iterator(); iterator.hasNext();){
			String key = (String) iterator.next();
			JSONObject currentWordInfo = new JSONObject();
			for (JSONObject objectWord: listOfScores){
				if (key.equals(objectWord.get("document"))){
					currentWordInfo.put(objectWord.get("word"), objectWord);
				}
			}
			JsonScores.put(key, currentWordInfo);
		}
	}
	
	public List<String> getWords(){
		return listOfUniqueWords;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getNormalizedJson(){
		for (JSONObject objectWord: jsonList){
			double tfidf = (double) objectWord.get("tf-idf")/getMax();
			JSONObject tempObj = new JSONObject();
			tempObj.put("word", objectWord.get("word"));
			tempObj.put("tf-idf", tfidf);
			tempObj.put("document", objectWord.get("document"));
			normalizedJsonList.add(tempObj);
		}
		jsonCreator(true);
		return JsonScores;
	}
	
	public JSONObject getJson(){
		jsonCreator(false);
		return JsonScores;
	}
	
	public List<JSONObject> getList(){
		return jsonList;
	}
}
