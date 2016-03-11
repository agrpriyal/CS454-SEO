package cs454.webCrawler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

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
}
