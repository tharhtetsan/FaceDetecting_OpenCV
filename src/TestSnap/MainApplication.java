package TestSnap;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApplication extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader root = new FXMLLoader(getClass().getResource("View.fxml"));
		root.setController(new Controller());
		Scene scene = new Scene(((Parent) root.load()));

		primaryStage.setScene(scene);
		primaryStage.setTitle("Snap Shoot");
		
		primaryStage.setResizable(false);
		
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Application.launch(args);
	}
}
