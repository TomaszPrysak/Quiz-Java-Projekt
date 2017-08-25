package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.TextField;
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
    private Spinner<Integer> sp_ip;
    
    @FXML
    private Label lb_rb;
    
    @FXML
    private Label lb_sp;
    
    @FXML
    private VBox zakresPytan;
    
    public DBConnector db;
    
    String imie;
    String nazwisko;
    
    @FXML
    void loginAction(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
    	
    	imie = tf_imie.getText();
    	nazwisko = tf_nazwisko.getText();
    	
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where imie = '" + imie + "' and nazwisko = '" + nazwisko + "';");
    	if(rs.next()){
    		zakresPytan.setDisable(false);
    		btn_gra.setDisable(false);
    		lb_rb.setDisable(false);
    		lb_sp.setDisable(false);
    		sp_ip.setDisable(false);
    	}else{
    		Alert a = new Alert(AlertType.WARNING);
    		a.setContentText("Poda³eœ b³êdny login lub has³o");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    }
    
    static Boolean db_sel= false;
    static Boolean python_sel = false;
    static Boolean fe_sel= false;
    static Boolean java_sel = false;
    static Boolean spring_sel = false;
    
    //ObservableList<Integer> months;
    //SpinnerValueFactory<Integer> valueFactory;
    
    @FXML
    void graAction(MouseEvent event) throws ClassNotFoundException, IOException {
    	
    	if(cb_db.isSelected()){
    		db_sel = true;
    	}
    	if(cb_python.isSelected()){
    		python_sel = true;
    	}
    	if(cb_fe.isSelected()){
    		fe_sel = true;
    	}
    	if(cb_java.isSelected()){
    		java_sel = true;
    	}
    	if(cb_spring.isSelected()){
    		spring_sel = true;
    	}

		Stage stageGame = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/QuizView.fxml"));
		Scene sceneGame = new Scene(parent);
		stageGame.setScene(sceneGame);
		stageGame.setTitle("QuizView");
		stageGame.setResizable(false);
		stageGame.show();
	}
    
    static int qty; // iloœæ pytañ wybrana przez u¿ytkownika
    
    public void initialize(){
    	db = new DBConnector();
    	
    	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3);
    	
    	sp_ip.setValueFactory(valueFactory);
    	
    	qty = sp_ip.getValue(); // iloœæ pytañ wybrana przez u¿ytkownika
    	
    }
    
}
