package app.controller;

import javafx.fxml.FXML;

public class QuizController {
	
	@FXML
	void initialize(){
		LoginController lc = new LoginController();
		System.out.println(lc.db_sel);
	}
	
}
