/**
 * An GUI view of the Boggle game
 * User will see the dice tray, can enter attempts in the text area
 * Scores will show after clicked end game button
 * @author Chaoneng Quan
 */

package view_controller;

import model.Boggle;
import model.DiceTray;
import model.ShuffleDiceTray;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoggleApp extends Application {

	private ShuffleDiceTray SDT;
	private DiceTray dt;

	private Label prompt = new Label("Enter your attempts below:");
	private Button newGameButton = new Button("New Game");
	private Button endGameButton = new Button("End Game");
	private TextArea diceTrayDisplay = new TextArea();
	private TextArea input = new TextArea();

	private final double SCENE_WIDTH = 350;
	private final double SCENE_HEIGHT = 750;
	private final int GRIDPANE_HGAP = 20;
	private final int GRIDPANE_VGAP = 20;
	private final double TEXTAREA_WIDTH = 300;
	private final double TEXTAREA_HEIGHT = 300;

	/**
	 * main method to show the GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * start method of the GUI
	 */
	public void start(Stage stage) {
		// Create a new dice tray
		shuffleDiceTray();
		// Display the dice tray
		setDiceTrayDisplay(diceTrayDisplay);
		// Create a GridPane and layout its components
		GridPane pane = new GridPane();
		layoutComponents(pane);
		// Add pane to a Scene
		Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
		// Add scene to stage
		stage.setScene(scene);
		stage.show();

//		//Hard code 2d Array for testing
//		char[][] fixed = { { 'S', 'V', 'O', 'R' }, { 'L', 'E', 'L', 'G' }, { 'T', 'O', 'N', 'U' },
//				{ 'I', 'O', 'E', 'I' }, };
//		dt = new DiceTray(fixed);
//		String output ="";
//		for(int r =0; r < 4; r++) {
//			for(int c = 0; c < 4; c++) {
//				output += fixed[r][c]+ " ";
//			}
//			output += "\n";
//		}
//		diceTrayDisplay.setText(output);
//		diceTrayDisplay.setFont(new Font("Courier", 39));
	}

	/**
	 * lay out the components on the grid pane
	 * 
	 * @param gp the grid pane which all component laid on
	 */
	private void layoutComponents(GridPane gp) {
		// gp.setGridLinesVisible(true);
		gp.setHgap(GRIDPANE_HGAP);
		gp.setVgap(GRIDPANE_VGAP);

		// Setting text area's height and width
		diceTrayDisplay.setPrefHeight(TEXTAREA_HEIGHT);
		diceTrayDisplay.setPrefWidth(TEXTAREA_WIDTH);
		input.setPrefHeight(TEXTAREA_HEIGHT);
		input.setPrefWidth(TEXTAREA_WIDTH);

		// Numbers are the coordinates of the each objects
		gp.add(diceTrayDisplay, 1, 1, 3, 3);
		gp.add(newGameButton, 1, 4);
		gp.add(new Text("                          "), 2, 5);
		gp.add(endGameButton, 3, 4);
		gp.add(prompt, 1, 5, 2, 1);
		gp.add(input, 1, 6, 3, 3);

		// Button set on action
		newGameButton.setOnAction(new NewGameButtonHandler());
		endGameButton.setOnAction(new EndGameButtonHandler());

	}

	/**
	 * Display the current dice tray to the upper text area to user
	 * 
	 * @param ta
	 */
	private void setDiceTrayDisplay(TextArea ta) {
		int fontSize = 41;
		ta.setText(SDT.toString());
		ta.setEditable(false);
		ta.setFont(new Font("Courier", fontSize));
		ta.setWrapText(true);
	}

	/**
	 * Shuffle the dice, create a new dice tray
	 */
	private void shuffleDiceTray() {
		SDT = new ShuffleDiceTray();
		dt = new DiceTray(SDT.getRandomDiceTrat());
	}

	/**
	 * inner class for new game button, when new game is clicked shuffle the dice
	 * create a new dice tray then display it
	 * 
	 * @author Chaoneng Quan
	 */
	private class NewGameButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			shuffleDiceTray();
			setDiceTrayDisplay(diceTrayDisplay);
			input.clear();
		}
	}

	/**
	 * inner class for end game button, when end game is clicked call the boggle
	 * console based game use input from the input text area, and return scores and
	 * other results
	 * 
	 * @author Chaoneng Quan
	 */
	private class EndGameButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Boggle game = new Boggle(input, dt);
			// Alert pop up
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Results");
			alert.setHeaderText("Here is your score");
			alert.setContentText(game.output()); // message is a big string with all required reults
			alert.showAndWait();

		}

	}

}