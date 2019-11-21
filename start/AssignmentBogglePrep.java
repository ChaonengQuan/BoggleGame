package start;

/**
 * Complete an event driven GUI that could be the start of
 * a GUI for Boggle Three
 * 
 * author Rick Mercer and Chaoneng Quan
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AssignmentBogglePrep extends Application {

  public static void main(String[] args) {
    launch(args);
  }


  private Image boggleImage = new Image("boggle.jpeg");
  private TextArea input = new TextArea();
  private TextArea output = new TextArea();
  private Label prompt = new Label("Enter sent get ten notHere");
  private Button button = new Button("Search for found words");
  ArrayList<String> boggleWords = new ArrayList<String>();

  /**
   * Layout the GUI and initialize everything (this is too much in start)
   */
  @Override
  public void start(Stage stage) {
    makeListOfBoggleWords();
    input.setWrapText(true); // Cause a newLine instead of going off the TextArea
    // Add elements in column 1, not 0 with gaps set
    GridPane pane = new GridPane();
    
    layoutComponents(pane);
    addHandler();
    
    Scene scene = new Scene(pane,600,800);
    stage.setScene(scene);
    stage.show();
    
   }

  private void makeListOfBoggleWords() {
    boggleWords.add("sent");
    boggleWords.add("ten");
    boggleWords.add("get");
  }
  
  private void layoutComponents(GridPane gp) {
	  //gp.setGridLinesVisible(true);
	  gp.setHgap(20);
	  gp.setVgap(20);
	  gp.add(new ImageView(boggleImage),1,1);
	  gp.add(prompt, 1, 3);
	  gp.add(input, 1, 4);
	  gp.add(button, 1, 5);
	  gp.add(output, 1, 6);
		
  }
  
  private void addHandler() {
	  button.setOnAction(new buttonHandler());
  }
  

  private class buttonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			Set<String> inputWords = new TreeSet<String>();
			
			Scanner keyboard = new Scanner(input.getText());
			while(keyboard.hasNext()) {
				String word = keyboard.next();
				if(boggleWords.contains(word))
					inputWords.add(word);
			}
			keyboard.close();
			
			
			String outputWords = "You found: ";
			for(String word: inputWords) {
				outputWords += word + " ";
			}
			
			output.setText(outputWords);
		}
	}
  

  
}