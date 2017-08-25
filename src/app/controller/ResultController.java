	package app.controller;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.database.DBConnector;
import javafx.fxml.FXML;
	import javafx.scene.control.Label;

	public class ResultController {

	    @FXML
	    private Label r_imie;

	    @FXML
	    private Label r_nazwisko;

	    @FXML
	    private Label r_wynik;

	    @FXML
	    private Label s_procent;

	    @FXML
	    private Label s_gratulacje;
	    
	    @FXML
	    private Label s_gry;
	    
	    public static DBConnector db;
	    
	    @FXML
	    void initialize() throws ClassNotFoundException, SQLException{
	    	
	    	db = new DBConnector();
	    	
	    	Connection conn1 = db.Connection();
	    	
	    	Statement stat = conn1.createStatement();
	    	ResultSet rs1 = stat.executeQuery("select * from users where imie = '" + LoginController.imie + "' and nazwisko = '" + LoginController.nazwisko + "';");
	    	rs1.next();
	    	
	    	int id_user = rs1.getInt(1);
	    	
	    	ResultSet rs2 = stat.executeQuery("select sum(res) from result group by id_user having id_user = '" + id_user + "';");
	    	rs2.next();
	    	
	    	double sum_points_user = rs2.getDouble(1);

	    	ResultSet rs3 = stat.executeQuery("select count(id_user) from result group by id_user having id_user = '" + id_user + "';");
	    	rs3.next();
	    	
	    	int qty_game_user = rs3.getInt(1);
	    	
	    	System.out.println(sum_points_user);
	    	
	    	System.out.println(qty_game_user);
	    	
	    	String sql = "insert into result (id_user, res) values (" + id_user + ", " + QuizController.correct_answer + ");";
	    	PreparedStatement ps = conn1.prepareStatement(sql);
	    	ps.executeUpdate();
	    	
	    	s_gry.setText(String.valueOf(qty_game_user));
	    	
	    	r_imie.setText(LoginController.imie);
	    	
	    	r_nazwisko.setText(LoginController.nazwisko);
	    	
	    	r_wynik.setText(String.valueOf(QuizController.correct_answer) + " / " + String.valueOf(LoginController.qty_question));
	    	
	    }
	    
	}
