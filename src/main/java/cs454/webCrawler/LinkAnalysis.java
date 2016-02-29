package cs454.webCrawler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkAnalysis {
	
	private File[] htmlFiles;
	
	public LinkAnalysis(String directory){
		File file = new File(directory);
		htmlFiles = file.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				return name.endsWith("html");
			}
		});
		for (File f: htmlFiles){
			parse(f);
		}
	}
	
	public void parse(File fileIn){
		Document doc = null;
		try {
			doc = Jsoup.parse(fileIn, "UTF-8");
		} catch (IOException e) {
			System.out.println("Unable to parse the file.");
			e.printStackTrace();
		}
		String title = doc.title();
		System.out.println(title);
		Elements links = doc.select("a[href]");
		for (Element link : links){
			System.out.println("\nlink: " + link.attr("href"));
			//System.out.println("text : " + link.text());
		}
	}
}
