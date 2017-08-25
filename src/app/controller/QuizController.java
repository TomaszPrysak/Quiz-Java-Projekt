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
    
    static int num_click_and_num_next_pyt = 1; // deklaracja i inicjalizacja zmiennej w któr¹ bêdziemy u¿ywaæ do przechowywania iloœci klikniêæ przycisku "Next" - przejœcie do nastêpnego pytania, ustawiona na pocz¹tku na 1 poniewa¿ odpalenie quizu od razu nam wyœwietla pierwsze pytanie
    
	public static int click(){ // metoda która zwiêksza wartoœæ zmiennej przchowuj¹cej iloœæ klikniêæ
		
		return num_click_and_num_next_pyt++; // zwraca aktualn¹ iloœæ klikniêæ
	
	}
	
	static int correct_answer = 0; // deklaracja i inicklaizacja zmienej w której bêdziemy przechowywaæ iloœæ poprawnych odpowiedzi udzielonych w quizie
	
	public void check_correct() throws ClassNotFoundException, SQLException{ // metoda w ktrórej sprawdzamy czy u¿ytkownik udzieli³ poprawnej odpowiedzi na aktualnie wyœwietlane 

		db = new DBConnector(); 
		Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs1 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy aby dostaæ dane dotycz¹cego konkretnego pytania
		rs1.next(); //
		
		String correct = rs1.getString("odp"); // przypisanie do zmiennej prawid³owej odpowiedzi na konkretne pytanie
    	
		String user_choice = ""; // deklaracja i inicjalizacja zmiennej w której bêdziemy przechowywaæ udzielon¹ odpowiedz u¿ytkownika
		
		// poni¿ej sprawdzenie który radiobutton wybra³ u¿ytkownik i przypisanie odpowiedzi do zmiennej user_choice
		
		if(rb_A.isSelected()){
			user_choice = "a";
		}else if(rb_B.isSelected()){
			user_choice = "b";
		}else if(rb_C.isSelected()){
			user_choice = "c";
		}else{
			user_choice = "d";
		}
		
		// poni¿ej sprawdzenie czy wybór uzytkownika jest taki sam jak prawid³owa odpowiedz, je¿eli tak to zwiêkszamy nasza zmienna przechowuj¹c¹ ilosæ poprawnych odpowiedzi u¿ytkownika
		
		if(user_choice.equals(correct)){
			correct_answer++;
			
		}
		
	}
    
    @FXML
    void nextAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException { // metoda do obs³ugi zdarzen w momencie nacisniecia przycisku "Next"
    	
    	check_correct(); // po klikniêciu przycisku uruchamiana jest metora która sprawdza czy udzielona odpowiedz na poprzednie pytanie jest prawid³owa
    	
    	if(click() != LoginController.qty_question){ // sprawdzenie czy iloœæ klikniêæ w przycisk Next jest rózna od ca³kowitej iloœci pytañ, w ten sposób sprawdzamy czy mamy wyœweitliæ kolejne pytanie, sprwdzenie odbywa siê poprzez prównanie iloœci wybranych pytañ przerz u¿ytkownika oraz iloœci klikniêæ przycisku Next co symblizuje iloœæ udzielonych opowiedzi na dotychczasowe
    		
    		nr_pyt.setText(String.valueOf(num_click_and_num_next_pyt)); // ustawienie na labelu numeru aktualnego pytania
    		
    		db = new DBConnector();
    		
    		Random gen = new Random();
    		
        	Connection conn1 = db.Connection();
        	Statement stat = conn1.createStatement();
        	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;"); // zapytanie do bazy danych w celu sprawdzenia iloœci wszystkich pytañ
        	rs1.next();
        	
        	int ilosc_pyt = rs1.getInt(1); // przypisujemy do zmiennej wynik zapytania powy¿szego
        	
        	los_pyt = gen.nextInt(ilosc_pyt); // losujemy liczbê z zakresu od 1 do iloœci wszystkich pytañ wystêpuj¹cych w bazie danych
        	
        	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy w celu odszukania pytania o numerze wylosowanym przez generator
        	rs2.next();
        	
        	lb_pyt.setText(rs2.getString("tresc")); // wyœwietlenie treœci wylosowanego pytania
    	    rb_A.setText(rs2.getString("a")); // wyœwietlenie mo¿liwej odpowiedzi a na wyklosowane pytanie
    	    rb_B.setText(rs2.getString("b")); // wyœwietlenie mo¿liwej odpowiedzi b na wyklosowane pytanie
    	    rb_C.setText(rs2.getString("c")); // wyœwietlenie mo¿liwej odpowiedzi c na wyklosowane pytanie
    	    rb_D.setText(rs2.getString("d")); // wyœwietlenie mo¿liwej odpowiedzi d na wyklosowane pytanie
    	    
    	} else {
    		
    		// w momencie kiedy iloœæ klikniêæ w przycisk jest równa iloœci wybranych pytañ, uruchamiamy wówczas widok z wynikami i uruchamiamy ResultController
    		
    		Stage stageResult = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/ResultView.fxml"));
    		Scene sceneResult = new Scene(parent);
    		stageResult.setScene(sceneResult);
    		stageResult.setTitle("ResultView");
    		stageResult.setResizable(false);
    		stageResult.show();	
    	}
    }
    
    static int los_pyt; // deklarujemy zmienn¹ w której bêdziemy przechowywaæ wylosowane pytanie przez generator
    
    public static DBConnector db;
    
	@FXML
	void initialize() throws SQLException, ClassNotFoundException, IOException{ // metoda pocz¹tkowa uruchamiana tylko raz w momencie startu widoku QuizView i od razu bêdzie nam pokazywaæ pierwsze pytanie
		
		db = new DBConnector();
		
		Random gen = new Random(); // tworzymy obiekt klasy Random do losowania pytañ z bazy danych
		
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs1 = stat.executeQuery("select count(id_q) from questions;"); // zapytanie do bazy danych w celu sprawdzenia iloœci wszystkich pytañ
    	rs1.next();
    	
    	int ilosc_pyt = rs1.getInt(1); // przypisujemy do zmiennej wynik zapytania powy¿szego
    	
    	los_pyt = gen.nextInt(ilosc_pyt + 1); // losujemy liczbê z zakresu od 1 do iloœci wszystkich pytañ wystêpuj¹cych w bazie danych
    	
    	ResultSet rs2 = stat.executeQuery("select * from questions where id_q = " + los_pyt + ";"); // zapytanie do bazy w celu odszukania pytania o numerze wylosowanym przez generator
    	rs2.next();
    	
    	lb_pyt.setText(rs2.getString("tresc")); // wyœwietlenie treœci wylosowanego pytania
	    rb_A.setText(rs2.getString("a")); // wyœwietlenie mo¿liwej odpowiedzi a na wyklosowane pytanie
	    rb_B.setText(rs2.getString("b")); // wyœwietlenie mo¿liwej odpowiedzi b na wyklosowane pytanie
	    rb_C.setText(rs2.getString("c")); // wyœwietlenie mo¿liwej odpowiedzi c na wyklosowane pytanie
	    rb_D.setText(rs2.getString("d")); // wyœwietlenie mo¿liwej odpowiedzi d na wyklosowane pytanie

    	sum_pyt.setText(String.valueOf(LoginController.qty_question)); // ustawienie na labelu iloœci wszyskich pytañ
    	
    	nr_pyt.setText(String.valueOf(num_click_and_num_next_pyt)); // ustawienie na labelu numer aktualnego pytania
    	
    	
	
	}
	
}
