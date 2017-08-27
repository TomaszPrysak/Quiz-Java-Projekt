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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    
    public DBConnector db; // deklaracja zmiennej db klasy DBConnector potrzebnej do utworzenia obiektu tej klasy
    
    static String imie; // deklaracja zmiennej w kt�rej przechowywane b�dzie imie u�ytkownika wprowadzone w ekranie logowania
    static String nazwisko; // podobnie jak powy�ej tylko przechowywane b�dzie nazwisko 
    
    @FXML
    void loginAction(MouseEvent event) throws ClassNotFoundException, SQLException, IOException { // metoda do obs�ugi zdarze� wykonywanych w momencie klikni�cia Przycisku "Loguj" w ekranie logowania
    	
    	imie = tf_imie.getText(); // zaci�gni�cie do zmiennej tekstu wpisanego w polu tekstowym IMIE
    	nazwisko = tf_nazwisko.getText(); // zaci�gni�cie do zmiennej tekstu wpisanego w polu tekstowym NAZWISKO
    	
    	Connection conn1 = db.Connection(); // utworzenie obiektu conn1 do utowrzenia po��czenia  
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where imie = '" + imie + "' and nazwisko = '" + nazwisko + "';"); // wykonanie zapytania do bazy danych gdzie imieniem i nazwiskiem s� warto�ci podane przez u�ytkownika w ekranie logowania 
    	
    	if(rs.next()){ // sprawdzenie czy istnieje rekord w bazie danych na podstawie powy�szego zapytania, je�eli istnieje to:
    		
    		zakresPytan.setDisable(false); //aktywowane s� checkboxy do wyboru zakresu tematyki quizu
    		btn_gra.setDisable(false); // aktywowany jest przycisk do wystartowania gry
    		lb_rb.setDisable(false); // aktywowana etykieta 
    		lb_sp.setDisable(false); // aktywowana etykieta
    		sp_ip.setDisable(false); // aktywowane okienko do wyboru ilo�ci pyta� w quizie
    		
    	}else{ // je�eli wprowadzone przez u�ytkownika dane s� b��dne to wy�wietla komunikat o b��dzie
    		
    		Alert a = new Alert(AlertType.WARNING);
    		a.setContentText("Poda�e� b��dny login lub has�o");
    		a.setTitle("B��d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    		
    	}
    }
    
    static Boolean db_sel= false; // deklaracja zmiennej w kt�rej b�dzie przechowywany wyb�r u�ytkownika w zakresie tematyki quizu
    static Boolean python_sel = false; // deklaracja zmiennej w kt�rej b�dzie przechowywany wyb�r u�ytkownika w zakresie tematyki quizu
    static Boolean fe_sel= false; // deklaracja zmiennej w kt�rej b�dzie przechowywany wyb�r u�ytkownika w zakresie tematyki quizu
    static Boolean java_sel = false; // deklaracja zmiennej w kt�rej b�dzie przechowywany wyb�r u�ytkownika w zakresie tematyki quizu
    static Boolean spring_sel = false; // deklaracja zmiennej w kt�rej b�dzie przechowywany wyb�r u�ytkownika w zakresie tematyki quizu
    
    @FXML
    void graAction(MouseEvent event) throws ClassNotFoundException, IOException { // metoda do obs�ugi zdarze� wykonywanych w momencie klikni�cia przycisku "Graj" w ekranie logowania, jednak przycisk ten jest aktywowany dopiero po wpisaniu prawid�owych danych przez u�ytkownika (tzn. danych istniej�cych w bazie danych gry)
    	
    	if(cb_db.isSelected() || cb_python.isSelected() || cb_fe.isSelected() || cb_java.isSelected() || cb_spring.isSelected()){
    	
	    	qty_question = sp_ip.getValue(); // przypisanie ilo�ci pyta� do zmiennej - ilo�� pyta� wybrana przez u�ytkownika za pomoc� "okienka"
	    	
	    	if(cb_db.isSelected()){ // sprawdza czy zaznaczona jest tematyka database
	    		db_sel = true; // je�eli tak to ustawia zmienn� na true
	    	}
	    	if(cb_python.isSelected()){ // sprawdza czy zaznaczona jest tematyka python
	    		python_sel = true; // je�eli tak to ustawia zmienn� na true
	    	}
	    	if(cb_fe.isSelected()){ // sprawdza czy zaznaczona jest tematyka front end
	    		fe_sel = true; // je�eli tak to ustawia zmienn� na true
	    	}
	    	if(cb_java.isSelected()){ // sprawdza czy zaznaczona jest tematyka java
	    		java_sel = true; // je�eli tak to ustawia zmienn� na true
	    	}
	    	if(cb_spring.isSelected()){ // sprawdza czy zaznaczona jest tematyka spring
	    		spring_sel = true; // je�eli tak to ustawia zmienn� na true
	    	}
	    	
	    	// nast�pnie uruchamiane jest okienko z pytaniami quizu i jednoczen�nie uruchamiany QuizController
	    	
			Stage stageGame = new Stage();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/QuizView.fxml"));
			Scene sceneGame = new Scene(parent);
			stageGame.setScene(sceneGame);
			stageGame.setTitle("QuizView");
			stageGame.setResizable(false);
			stageGame.show();
    	}else{
    		
    		Alert a = new Alert(AlertType.WARNING);
    		a.setContentText("Nie wybra�e� �adnej kategori pyta�");
    		a.setTitle("B��d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    		
    	}
	}
    
    static int qty_question; // deklaracja zmiennej w kt�ej b�dzie przechowywana ilo�� pyta� wybrana przez u�ytkownika za pomoc� "okienka" w oknie logowania
    
    public void initialize(){ // metoda pocz�tkowa uruchamiana tylko raz w momencie startu widoku LogowanieView
    	
    	db = new DBConnector(); // utworzenie obiektu "db" klasy DBConnector - jest to osobna klasa do wykonywania po��czenia z baz� danych (lokalizacja: app.database)
    	
    	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3); // utworzenie obiektu valueFactor klasy SpinnerValueFactor do deklaracji ile u�ytkownik b�dzie m�g� wybra� z "okienka" w menu logowania
    	
    	sp_ip.setValueFactory(valueFactory); // przypisanie powy�szej ilo�ci do "okienka"	
    	
    }
    
}
