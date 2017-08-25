package app.model;

public class QuizModel {
	
	private int id_q;
	private String tresc;
	private String a, b, c, d;
	private char odp;
	private String kat;
	
	public QuizModel() {
		super();
	}
	
	public QuizModel(int id_q, String tresc, String a, String b, String c, String d, char odp, String kat) {
		super();
		this.id_q = id_q;
		this.tresc = tresc;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.odp = odp;
		this.kat = kat;
	}
	
	public int getId_q() {
		return id_q;
	}
	public void setId_q(int id_q) {
		this.id_q = id_q;
	}
	public String getTresc() {
		return tresc;
	}
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public char getOdp() {
		return odp;
	}
	public void setOdp(char odp) {
		this.odp = odp;
	}
	public String getKat() {
		return kat;
	}
	public void setKat(String kat) {
		this.kat = kat;
	}
	
	
	
}
