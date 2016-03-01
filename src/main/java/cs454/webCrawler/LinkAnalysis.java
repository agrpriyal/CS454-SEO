package cs454.webCrawler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkAnalysis {
	
	private File[] htmlFiles;
	private Map<String, String> urlMap = new HashMap<String, String>();
	private Map<String, HashSet<String>> outMap = new HashMap<String, HashSet<String>>();
	private Map<String, HashSet<String>> inMap = new HashMap<String, HashSet<String>>();
	
	public LinkAnalysis(String directory, HashMap<String, String> urlMap){
		File file = new File(directory);
		this.urlMap.putAll(urlMap);
		htmlFiles = file.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				return name.endsWith("html");
			}
		});
		for (File f: htmlFiles){
			parse(f);
		}
		findInLinks();
	}
	
	public void parse(File fileIn){
		Document doc = null;
		try {
			doc = Jsoup.parse(fileIn, "UTF-8");
		} catch (IOException e) {
			System.out.println("Unable to parse the file.");
			e.printStackTrace();
		}
		String title = fileIn.getName();
		Elements links = doc.select("a[href]");
		Set<String> uniqueLinks = new HashSet<String>();
		for (Element link : links){
			String linkString = link.attr("href");
			uniqueLinks.add(linkString);
		}
		outMap.put(title, (HashSet<String>) uniqueLinks);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void findInLinks(){
		Iterator iterator = urlMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			Iterator outIt = outMap.entrySet().iterator();
			Set<String> ref = new HashSet<String>();
			while (outIt.hasNext()){
				Map.Entry outEntry = (Map.Entry)outIt.next();
				
				String currentUrl = (String) entry.getValue();
				Set<String> listOfLinks = (Set<String>) outEntry.getValue();
				for (String link: listOfLinks){
					//prevents counting a html page linking to itself
					if (entry.getKey().equals(outEntry.getKey())){
						continue;
					}
					else if (link.equals(currentUrl)){
						String keyString = (String) outEntry.getKey();
						ref.add((String) urlMap.get(keyString));
					}
					else {
						String linkString = link;
						if (linkString.charAt(linkString.length() - 1) == '/'){
							linkString = linkString.substring(0, linkString.length() - 1);
							if (linkString.equals(currentUrl)){
								String keyString = (String) outEntry.getKey();
								ref.add((String) urlMap.get(keyString));
							}
						}
						else if (linkString.charAt(linkString.length() - 1) == '#'){
							String tempString = linkString.substring(0, linkString.length() - 1);
							if (tempString.equals(currentUrl)){
								String keyString = (String) outEntry.getKey();
								ref.add((String) urlMap.get(keyString));
							}
							if (tempString.charAt(tempString.length() - 1) == '/'){
								linkString = tempString.substring(0, tempString.length() - 1);
								if (linkString.equals(currentUrl)){
									String keyString = (String) outEntry.getKey();
									ref.add((String) urlMap.get(keyString));
								}
							}
						}
					}
				}				
		}
			inMap.put(entry.getValue().toString(), (HashSet<String>) ref);
		}
	}
	
	public Map<String, HashSet<String>> getInMap(){
		return inMap;
	}
}