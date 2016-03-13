package cs454.webCrawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchGui extends Application{
	private static Map<String, Page> linkMap = new HashMap<String, Page>();
	private static Map<String, HashMap<String, Word>> weightMap = new HashMap<String, HashMap<String, Word>>();
	private static Map<String, String> urlMap = new HashMap<String, String>();
	private static Map<Integer, Hyperlink> hyperMap = new HashMap<Integer, Hyperlink>();
	private static Map<String, Double> pageTotalScore = new HashMap<String, Double>();
	private static List<Hyperlink> hyperLinks = new ArrayList<Hyperlink>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		HBox header = new HBox();
		final HBox centerScreen = new HBox();
		final VBox vp = new VBox();
		ScrollPane scrollPane = new ScrollPane(bp);
		scrollPane.setFitToHeight(true);
		Scene sc = new Scene(scrollPane);
		sc.getStylesheets().add("styles/style.css");
		final TextField text = new TextField();
		Label enterText = new Label("What are you looking for?:");
		Button submit = new Button("Submit");
		Label heading = new Label("Elgoog Enigne Search");
		Label yourText = new Label("");
		final Label response = new Label("");
		final Label response2 = new Label("");
		vp.getChildren().add(yourText);
		centerScreen.getChildren().add(enterText);
		centerScreen.getChildren().add(text);
		centerScreen.getChildren().add(submit);
		header.getChildren().add(heading);
		heading.getStyleClass().add("label2");
		enterText.getStyleClass().add("label3");
		submit.getStyleClass().add("button");//this actually doesn't do anything! Padding doesn't work on buttons?
		yourText.getStyleClass().add("label4");
		response.getStyleClass().add("label5");
		response2.getStyleClass().add("label5");
		header.getStyleClass().add("hb");
		vp.getStyleClass().add("hb");
		bp.setTop(header);
		bp.setCenter(centerScreen);
		bp.setBottom(vp);
		
		for(int i = 0; i < 30; i++){
			final Hyperlink link = new Hyperlink();
			vp.getChildren().add(link);
			hyperMap.put(i, link);
		}
		vp.getChildren().add(response);
		vp.getChildren().add(response2);
		try{
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/Volfurious/Desktop/json/score json.txt"));
			Gson gson = new GsonBuilder().create();
			ScoreInfo scoreInfo = gson.fromJson(br, ScoreInfo.class);
			linkMap = scoreInfo.getLink();
			weightMap = scoreInfo.getWeight();
			Properties properties = new Properties();
			properties.load(new FileInputStream("map.properties"));
			for (String key : properties.stringPropertyNames()) {
				   urlMap.put(key, properties.get(key).toString());
				}

		} catch (Exception e){
			e.printStackTrace();
		}
		
		submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				for (Hyperlink clearThis: hyperLinks){
					clearThis.setText("");
				}
				hyperLinks.clear();
				pageTotalScore.clear();
				String userText = text.getText().toLowerCase();
				List<String> userWords = new LinkedList<String>(Arrays.asList(userText.split(" ")));
				
				for (String word: userWords){
					getScore(word);
				}
				List<String> allUrl = sortMap();
				for (int i = 0; i < allUrl.size(); i++){
					if (i == 30){
						break;
					}
					else{
					final Hyperlink link = hyperMap.get(i);
					hyperLinks.add(link);
						link.setText(allUrl.get(i));
						link.setOnAction(new EventHandler<ActionEvent>(){
							public void handle(ActionEvent t){
								getHostServices().showDocument(link.getText());
							}
						});
					}
					
					
				}
				StringBuilder sb = new StringBuilder();
				for (Entry<String, Double> entry : pageTotalScore.entrySet()){
					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append("\n");
				}
				response.setText("All Document Scores");
				response2.setText(sb.toString());
				
				
			}
		});
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	public static void main(String[] args){
		Application.launch(args);
	}
	

	public List<String> sortMap(){
		List<String> urlOrder = new ArrayList<String>();
		Set<Entry<String, Double>> set = pageTotalScore.entrySet();
		List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
				{
					public int compare(Map.Entry<String, Double> value1, Map.Entry<String, Double> value2)
					{
						return (value2.getValue()).compareTo(value1.getValue());
					}
				}
				);
		for(Map.Entry<String, Double> entry:list){
			urlOrder.add(entry.getKey());
		}
		return urlOrder;
	}
	
	public void getScore(String query){
		for (Entry<String, HashMap<String, Word>> entry : weightMap.entrySet()){
			HashMap<String, Word> listOfWords = entry.getValue();
			if (listOfWords.containsKey(query) && listOfWords.get(query) != null){
				Word currentWord = listOfWords.get(query);
				double tfidf = currentWord.getTfidf();
				String currentUrl = urlMap.get(currentWord.getDocument());
				Page currentPage = linkMap.get(currentUrl);
				double pageScore = currentPage.getScore();
				if (pageTotalScore.get(currentUrl) != null){
					double currentScore = pageTotalScore.get(currentUrl);
					pageTotalScore.put(currentUrl, currentScore + tfidf);
				}
				else {
					pageTotalScore.put(currentUrl, tfidf + pageScore);
				}
			}
			else{
				continue;
			}
		}
	}
}
