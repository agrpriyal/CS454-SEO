package cs454.webCrawler;

public class WebCrawlerMain {

	public static void main( String[] args )
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
    }

}
