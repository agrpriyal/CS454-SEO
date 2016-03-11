package cs454.webCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SearchGui extends Application{
	private static JSONParser parser = new JSONParser();
	private static JSONObject jsonScores = new JSONObject();
	private static Map<String, Page> linkMap = new HashMap<String, Page>();
	private static Map<String, HashMap<String, Word>> weightMap = new HashMap<String, HashMap<String, Word>>();
	
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
		Label enterText = new Label("Please enter your text:");
		Button submit = new Button("Submit");
		Label heading = new Label("Elgoog Enigne Search");
		Label yourText = new Label("");
		final Label response = new Label("");
		final Label response2 = new Label("");
		vp.getChildren().add(yourText);
		vp.getChildren().add(response);
		vp.getChildren().add(response2);
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
		final Hyperlink link = new Hyperlink();
		vp.getChildren().add(link);
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/Volfurious/Desktop/json/score json.txt"));
			Gson gson = new GsonBuilder().create();
			ScoreInfo scoreInfo = gson.fromJson(br, ScoreInfo.class);
			linkMap = scoreInfo.getLink();
			weightMap = scoreInfo.getWeight();
			//HashMap<String, Word> wordMap = weightMap.get("uuid3.html");
			//Word word = wordMap.get("gift");
			//Page page = linkMap.get("http://www.pandora.com");
			//System.out.println(word.getTfidf() + " " + word.getDocument());
			//System.out.println(page.getUrl() + " " + page.getScore() + " " + page.getDocument());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				String userText = text.getText();
				List<String> userWords = new LinkedList<String>(Arrays.asList(userText.split(" ")));
				response.setText(userText);
				response2.setText(userWords.toString());
				link.setText("http://google.com");
				link.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent t){
						getHostServices().showDocument(link.getText());
						System.out.println(jsonScores.get("link"));
					}
				});
				
			}
		});
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	public static void main(String[] args){
		Application.launch(args);
	}
	

	public void getLinks(String query){
		List<String> words = new LinkedList<String>(Arrays.asList(query.split(" ")));
	}
}
