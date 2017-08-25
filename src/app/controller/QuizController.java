package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import app.database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizController {

    @FXML
    private Label lb_pyt;
	
    @FXML
    private VBox radiobox;
    
    @FXML
    private ToggleGroup rb1;

    @FXML
    private RadioButton rb_A;

    @FXML
    private RadioButton rb_B;

    @FXML
    private RadioButton rb_C;

    @FXML
    private RadioButton rb_D;

    @FXML
    private Button btn_next;

    @FXML
    private Label sum_pyt;

    @FXML
    private Label nr_pyt;

    public static DBConnector db;
    
    static int num_click = 1;
    
    static int num_next_pyt = num_click; // zmienna do przechowywania numery kolejnego pytania
    
	public static int click(){
		
		return num_click++;
	
	}
	
	static int correct_answer = 0;
	
	public void check_correct() throws ClassNotFoundException, SQLException{

		db = new DBConnector();
		Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs1 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";");
		rs1.next();
		
		String correct = rs1.getString("odp");
    	
		String user_choice = "";
		
		if(rb_A.isSelected()){
			user_choice = "a";
		}else if(rb_B.isSelected()){
			user_choice = "b";
		}else if(rb_C.isSelected()){
			user_choice = "c";
		}else{
			user_choice = "d";
		}
		
		if(user_choice.equals(correct)){
			correct_answer++;
			
		}
		
	}
    
    @FXML
    void nextAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    	
    	check_correct();
    	
    	if(click() != LoginController.qty){
    		
    		nr_pyt.setText(String.valueOf(num_click));
    		
    		db = new DBConnector();
    		
    		Random gen = new Random();
    		
        	Connection conn1 = db.Connection();
        	Statement stat = conn1.createStatement();
        	
        	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;");
        	rs1.next();
        	int ilosc_pyt = rs1.getInt(1);
        	
        	los_pyt = gen.nextInt(ilosc_pyt);
        	
        	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";");
        	rs2.next();
        	lb_pyt.setText(rs2.getString("tresc"));
    	    rb_A.setText(rs2.getString("a"));
    	    rb_B.setText(rs2.getString("b"));
    	    rb_C.setText(rs2.getString("c"));
    	    rb_D.setText(rs2.getString("d"));
    	} else {
    		Stage stageResult = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/ResultView.fxml"));
    		Scene sceneResult = new Scene(parent);
    		stageResult.setScene(sceneResult);
    		stageResult.setTitle("ResultView");
    		stageResult.setResizable(false);
    		stageResult.show();	
    	}
    }
    
    static int los_pyt;
    
	@FXML
	void initialize() throws SQLException, ClassNotFoundException, IOException{
		
		db = new DBConnector();
		
		Random gen = new Random();
		
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	
    	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;");
    	
    	rs1.next();
    	
    	int ilosc_pyt = rs1.getInt(1);
    	
    	los_pyt = gen.nextInt(ilosc_pyt + 1);
    	
    	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";");
    	rs2.next();
    	lb_pyt.setText(rs2.getString("tresc"));
	    rb_A.setText(rs2.getString("a"));
	    rb_B.setText(rs2.getString("b"));
	    rb_C.setText(rs2.getString("c"));
	    rb_D.setText(rs2.getString("d"));

    	sum_pyt.setText(String.valueOf(LoginController.qty));
    	
    	nr_pyt.setText(String.valueOf(num_next_pyt));
    	
    	
	
	}
	
}
