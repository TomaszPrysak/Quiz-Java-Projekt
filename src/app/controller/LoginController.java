package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
    private TextField tf_imie;

    @FXML
    private TextField tf_nazwisko;

    @FXML
    private Button btn_login;
    
    @FXML
    private Button btn_gra;
    
    @FXML
    private CheckBox cb_db;

    @FXML
    private CheckBox cb_python;

    @FXML
    private CheckBox cb_fe;
    
    @FXML
    private CheckBox cb_java;
    
    @FXML
    private CheckBox cb_spring;
    
    @FXML
    private Spinner<?> sp_ip;
    
    @FXML
    private VBox zakresPytan;
    
    public DBConnector db;
	
    @FXML
    void loginAction(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
    	
    	Connection conn1 = db.Connection();
    	
    	Statement stat = conn1.createStatement();
    	
    	ResultSet rs = stat.executeQuery("select * from users where imie = '" + tf_imie.getText() + "' and nazwisko = '" + tf_nazwisko.getText() + "';");
    
    	if(rs.next()){
    		
    		zakresPytan.setDisable(false);
    		btn_gra.setDisable(false);
    		
    	}else{
    		Alert a = new Alert(AlertType.WARNING);
    		a.setContentText("Poda³eœ b³êdny login lub has³o");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    	
    }
    
    @FXML
    void graAction(MouseEvent event) throws ClassNotFoundException, IOException {
    	
		Stage stageGame = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/DataBaseView.fxml"));
		Scene sceneDB = new Scene(parent);
		stageGame.setScene(sceneDB);
		stageGame.setTitle("DataBaseView");
		stageGame.setResizable(false);
		stageGame.show();
    	
	}
    
    public void initialize(){
    	db = new DBConnector();
    }
    
}
