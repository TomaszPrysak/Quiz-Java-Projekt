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
    
    static int num_click_and_num_next_pyt = 1; // deklaracja i inicjalizacja zmiennej w kt�r� b�dziemy u�ywa� do przechowywania ilo�ci klikni�� przycisku "Next" - przej�cie do nast�pnego pytania, ustawiona na pocz�tku na 1 poniewa� odpalenie quizu od razu nam wy�wietla pierwsze pytanie
    
	public static int click(){ // metoda kt�ra zwi�ksza warto�� zmiennej przchowuj�cej ilo�� klikni��
		
		return num_click_and_num_next_pyt++; // zwraca aktualn� ilo�� klikni��
	
	}
	
	static int correct_answer = 0; // deklaracja i inicklaizacja zmienej w kt�rej b�dziemy przechowywa� ilo�� poprawnych odpowiedzi udzielonych w quizie
	
	public void check_correct() throws ClassNotFoundException, SQLException{ // metoda w ktr�rej sprawdzamy czy u�ytkownik udzieli� poprawnej odpowiedzi na aktualnie wy�wietlane 

		db = new DBConnector(); 
		Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs1 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy aby dosta� dane dotycz�cego konkretnego pytania
		rs1.next(); //
		
		String correct = rs1.getString("odp"); // przypisanie do zmiennej prawid�owej odpowiedzi na konkretne pytanie
    	
		String user_choice = ""; // deklaracja i inicjalizacja zmiennej w kt�rej b�dziemy przechowywa� udzielon� odpowiedz u�ytkownika
		
		// poni�ej sprawdzenie kt�ry radiobutton wybra� u�ytkownik i przypisanie odpowiedzi do zmiennej user_choice
		
		if(rb_A.isSelected()){
			user_choice = "a";
		}else if(rb_B.isSelected()){
			user_choice = "b";
		}else if(rb_C.isSelected()){
			user_choice = "c";
		}else{
			user_choice = "d";
		}
		
		// poni�ej sprawdzenie czy wyb�r uzytkownika jest taki sam jak prawid�owa odpowiedz, je�eli tak to zwi�kszamy nasza zmienna przechowuj�c� ilos� poprawnych odpowiedzi u�ytkownika
		
		if(user_choice.equals(correct)){
			correct_answer++;
			
		}
		
	}
    
    @FXML
    void nextAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException { // metoda do obs�ugi zdarzen w momencie nacisniecia przycisku "Next"
    	
    	check_correct(); // po klikni�ciu przycisku uruchamiana jest metora kt�ra sprawdza czy udzielona odpowiedz na poprzednie pytanie jest prawid�owa
    	
    	if(click() != LoginController.qty_question){ // sprawdzenie czy ilo�� klikni�� w przycisk Next jest r�zna od ca�kowitej ilo�ci pyta�, w ten spos�b sprawdzamy czy mamy wy�weitli� kolejne pytanie, sprwdzenie odbywa si� poprzez pr�wnanie ilo�ci wybranych pyta� przerz u�ytkownika oraz ilo�ci klikni�� przycisku Next co symblizuje ilo�� udzielonych opowiedzi na dotychczasowe
    		
    		nr_pyt.setText(String.valueOf(num_click_and_num_next_pyt)); // ustawienie na labelu numeru aktualnego pytania
    		
    		db = new DBConnector();
    		
    		Random gen = new Random();
    		
        	Connection conn1 = db.Connection();
        	Statement stat = conn1.createStatement();
        	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;"); // zapytanie do bazy danych w celu sprawdzenia ilo�ci wszystkich pyta�
        	rs1.next();
        	
        	int ilosc_pyt = rs1.getInt(1); // przypisujemy do zmiennej wynik zapytania powy�szego
        	
        	los_pyt = gen.nextInt(ilosc_pyt); // losujemy liczb� z zakresu od 1 do ilo�ci wszystkich pyta� wyst�puj�cych w bazie danych
        	
        	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy w celu odszukania pytania o numerze wylosowanym przez generator
        	rs2.next();
        	
        	lb_pyt.setText(rs2.getString("tresc")); // wy�wietlenie tre�ci wylosowanego pytania
    	    rb_A.setText(rs2.getString("a")); // wy�wietlenie mo�liwej odpowiedzi a na wyklosowane pytanie
    	    rb_B.setText(rs2.getString("b")); // wy�wietlenie mo�liwej odpowiedzi b na wyklosowane pytanie
    	    rb_C.setText(rs2.getString("c")); // wy�wietlenie mo�liwej odpowiedzi c na wyklosowane pytanie
    	    rb_D.setText(rs2.getString("d")); // wy�wietlenie mo�liwej odpowiedzi d na wyklosowane pytanie
    	    
    	} else {
    		
    		// w momencie kiedy ilo�� klikni�� w przycisk jest r�wna ilo�ci wybranych pyta�, uruchamiamy w�wczas widok z wynikami i uruchamiamy ResultController
    		
    		Stage stageResult = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/ResultView.fxml"));
    		Scene sceneResult = new Scene(parent);
    		stageResult.setScene(sceneResult);
    		stageResult.setTitle("ResultView");
    		stageResult.setResizable(false);
    		stageResult.show();	
    	}
    }
    
    static int los_pyt; // deklarujemy zmienn� w kt�rej b�dziemy przechowywa� wylosowane pytanie przez generator
    
    public static DBConnector db;
    
	@FXML
	void initialize() throws SQLException, ClassNotFoundException, IOException{ // metoda pocz�tkowa uruchamiana tylko raz w momencie startu widoku QuizView i od razu b�dzie nam pokazywa� pierwsze pytanie
		
		db = new DBConnector();
		
		Random gen = new Random(); // tworzymy obiekt klasy Random do losowania pyta� z bazy danych
		
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;"); // zapytanie do bazy danych w celu sprawdzenia ilo�ci wszystkich pyta�
    	rs1.next();
    	
    	int ilosc_pyt = rs1.getInt(1); // przypisujemy do zmiennej wynik zapytania powy�szego
    	
    	los_pyt = gen.nextInt(ilosc_pyt + 1); // losujemy liczb� z zakresu od 1 do ilo�ci wszystkich pyta� wyst�puj�cych w bazie danych
    	
    	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy w celu odszukania pytania o numerze wylosowanym przez generator
    	rs2.next();
    	
    	lb_pyt.setText(rs2.getString("tresc")); // wy�wietlenie tre�ci wylosowanego pytania
	    rb_A.setText(rs2.getString("a")); // wy�wietlenie mo�liwej odpowiedzi a na wyklosowane pytanie
	    rb_B.setText(rs2.getString("b")); // wy�wietlenie mo�liwej odpowiedzi b na wyklosowane pytanie
	    rb_C.setText(rs2.getString("c")); // wy�wietlenie mo�liwej odpowiedzi c na wyklosowane pytanie
	    rb_D.setText(rs2.getString("d")); // wy�wietlenie mo�liwej odpowiedzi d na wyklosowane pytanie

    	sum_pyt.setText(String.valueOf(LoginController.qty_question)); // ustawienie na labelu ilo�ci wszyskich pyta�
    	
    	nr_pyt.setText(String.valueOf(num_click_and_num_next_pyt)); // ustawienie na labelu numer aktualnego pytania
    	
    	
	
	}
	
}
