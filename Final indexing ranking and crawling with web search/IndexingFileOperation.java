package cs454.indexing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IndexingFileOperation {
	
	@SuppressWarnings("unused")
	public static BodyContentHandler readDownloadedFile(String localpath) {
		Map<String, Object> metadata = new HashMap<String, Object>();

		File f = new File(localpath);

		BasicFileAttributes attr;
		try
		{
			Parser parser = new AutoDetectParser();

			long start = System.currentTimeMillis();
			BodyContentHandler handler = new BodyContentHandler(10000000);
			Metadata metadata1 = new Metadata();
			InputStream content = TikaInputStream.get(new File(f
					.getAbsolutePath()));
			try
			{
				parser.parse(content, handler, metadata1, new ParseContext());
			}
			catch (TikaException e)
			{

				e.printStackTrace();
			}
			catch (SAXException e)
			{
				e.printStackTrace();
			}

			attr = Files.readAttributes(Paths.get(f.getAbsolutePath()),
					BasicFileAttributes.class);

			return handler;
		}
		catch (IOException e)
		{
			System.out.println("Failed to read file ");
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public static void saveAsJSON(final Map<String,List<Words>> wordMap, File file1) throws IOException
	{
		JSONArray jsonArray;
		List<Words> words;
		JSONObject obj;
		JSONObject jsonObjectToPrint = new JSONObject();
		
		for(String word : wordMap.keySet())
		{
			jsonArray = new JSONArray();
			words =  wordMap.get(word);
			double totalSize = words.size();
			double termFreq = 0.0;
			for(Words w : words)
			{
				obj = new JSONObject();
				obj.put("localFileName", w.getLocalFileName().toString());
				obj.put("url", w.getUrl());
				obj.put("title", w.getTitle());
				obj.put("author", w.getAuthor());
				obj.put("description", w.getDesc());
				obj.put("Count", Integer.toString(w.getWordCount()));
				termFreq = w.getWordCount() / totalSize;
				termFreq =Double.parseDouble(new DecimalFormat("##.##").format(termFreq));
				obj.put("tfid", Double.toString(termFreq));
				jsonArray.add(obj);
			}
			jsonObjectToPrint.put(word, jsonArray);
			
		}
		
		JSONArray mainArray = new JSONArray();
		mainArray.add(jsonObjectToPrint);
		
		FileWriter file = new FileWriter(file1.getAbsolutePath(), true);
        try {
        	
        	Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();			
			String pretJson = prettyGson.toJson(mainArray);
        	
            file.write(pretJson);
            System.out.println("Done Writing into file");
            
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
		
	}
	

}
