	package app.controller;

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
	    void initialize(){
	    	
	    	LoginController lc = new LoginController();
	    	
	    	r_wynik.setText(String.valueOf(QuizController.correct_answer));
	    	
	    	r_imie.setText(lc.imie);
	    	
	    	r_nazwisko.setText(lc.nazwisko);
	    	
	    }
	    
	}
