package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

public class QuizController {

    @FXML
    private VBox radiobox;

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

    @FXML
    void nextAction(ActionEvent event) {

    }

	@FXML
	void initialize(){
		LoginController lc = new LoginController();
		System.out.println(lc.db_sel);
		System.out.println(lc.python_sel);
		System.out.println(lc.fe_sel);
		System.out.println(lc.java_sel);
		System.out.println(lc.spring_sel);
	}
	
}
