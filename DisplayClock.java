	
//U10416022
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class DisplayClock extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		// Create a clock and a label
		ClockPane clock = new ClockPane();

		// Place clock and label in border pane
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 280);
		primaryStage.setTitle("DisplayClock"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		// eventHandler for animation
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime();
		};
		// animation for rhe clock
		Timeline running = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
		running.setCycleCount(Timeline.INDEFINITE);
		running.play();
		
		// new text
		Text pause = new Text(10, 30, "pause");
		Text play = new Text(200, 30, "play");
		Text time = new Text(70, 275, "time:");
		pane.getChildren().addAll(pause, play, time);

		// add action(mousepressed)
		pause.setOnMousePressed(e -> {
			running.pause();
			String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
			Label lblCurrentTime = new Label(timeString);
			pane.setBottom(lblCurrentTime);
			BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
		});
		play.setOnMousePressed(e -> {
			running.play();
		});
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
