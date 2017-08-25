package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stageLogowanie) throws Exception {
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LogowanieView.fxml"));
		Scene sceneLogowanie = new Scene(parent);
		stageLogowanie.setScene(sceneLogowanie);
		stageLogowanie.setTitle("LogowanieView");
		stageLogowanie.setResizable(false);
		stageLogowanie.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}