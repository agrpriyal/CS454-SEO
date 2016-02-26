package cs454.webCrawler;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class WeightCalculator {

	private int numberOfDocuments = 0;
	private JSONObject documents;
	private List<String> listOfWords = new ArrayList<String>();//change this to set, so that it doesn't have repeating words
	
	public WeightCalculator(JSONObject JObj, int numberOfDocuments){
		this.numberOfDocuments = numberOfDocuments;
		this.documents = JObj;
	}
	
	//find all the words from all the documents
	public void findWords(){
		
	}
	
	public double calculateWeight(JSONObject currentDoc, String word){
		double df = 0;
		double idf = Math.log10(numberOfDocuments/df);
		double frequency = 0;
		double tf = Math.log(1 + frequency);
		double weight = tf * idf;
		return weight;
	}
}
