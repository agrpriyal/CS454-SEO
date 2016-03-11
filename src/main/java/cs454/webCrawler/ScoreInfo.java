package cs454.webCrawler;

import java.util.HashMap;

public class ScoreInfo {
	private HashMap<String, Page> link;
	private HashMap<String, HashMap<String, Word>> weight;
	public HashMap<String, Page> getLink() {
		return link;
	}
	public void setLink(HashMap<String, Page> link) {
		this.link = link;
	}
	public HashMap<String, HashMap<String, Word>> getWeight() {
		return weight;
	}
	public void setWeight(HashMap<String, HashMap<String, Word>> weight) {
		this.weight = weight;
	}
}
