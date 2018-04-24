import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//JOptionPane.showMessageDialog(null,"Error");

public class Funkcje {
	
	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:tenis.db";
    
    private static Connection conn;
    private static Statement stmt;
    
	String sql;
	int i, j;
	int maxRows = 200000;
	
	ReadFile rf = new ReadFile();
	DBTablePrinter tb = new DBTablePrinter();
	
	//--------------------------------------------------------------------------------------------------------------------------------	
	public int takeoption() {
		Scanner reader = new Scanner(System.in);
		int i = reader.nextInt();
		return i;
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public String takeoptiontext() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void wczytaj() throws SQLException{
		rf.connect();
		rf.create();
		rf.read();
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void wyswietl() throws SQLException{
		Connection conn = DriverManager.getConnection(DB_URL);
		DBTablePrinter.printTable(conn, "players", maxRows);
		DBTablePrinter.printTable(conn, "rank", maxRows);
    }
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void top() throws SQLException {
		
    	System.out.println("Ilu najlepszych graczy wypisa�?");
    	i=takeoption();
		try {
		Connection conn = DriverManager.getConnection(DB_URL);
		sql="SELECT currentrank, name, surname, country, points FROM players LEFT JOIN rank ON players.id=rank.id WHERE currentrank<="+i+" ORDER BY players.id ASC;";
		conn.prepareStatement(sql);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		DBTablePrinter.printResultSet(rs);
		}catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void changepoints() {
		
    	System.out.println("Wpisz id gracza, kt�remu chcesz zmieni� pkt");
    	i=takeoption();
    	System.out.println("Wpisz now� ilo�� punkt�w");
    	j=takeoption();
		sql="UPDATE rank SET points = "+ j +" WHERE id = "+ i +";";
		System.out.println(sql);
		rf.instrukcja(sql);
		
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void updatepoints() throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL);
		int a;
    	System.out.println("Wpisz id gracza, kt�remu chcesz doda� pkt");
    	i=takeoption();
    	System.out.println("Wpisz now� ilo�� punkt�w do dodania");
    	j=takeoption();
    	sql="SELECT points FROM rank WHERE id="+i+";";
    	conn.prepareStatement(sql);
    	Statement stmt = conn.createStatement();
    	conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		a=rs.getInt("points");
		a+=j;
		System.out.println(j);
		sql="UPDATE rank SET currentpoints= "+ a + " WHERE id="+ i +";";
		rf.instrukcja(sql);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void addplayer() throws IOException, SQLException {
    	String a, b, c, d;
    	System.out.println("Wpisz id gracza");
    	i=takeoption();
    	System.out.println("Wpisz imi�");
    	a=takeoptiontext();
    	System.out.println("Wpisz nazwisko");
    	b=takeoptiontext();
    	System.out.println("Wpisz r�k� preferowan� przez gracza (R/L/U)");
    	c=takeoptiontext();
    	System.out.println("Wpisz dat� urodzenia w formacie YYYYMMDD");
    	j=takeoption();
    	System.out.println("Wpisz 3-literowy skr�t nazwy kraju");
    	d=takeoptiontext();
		sql="INSERT INTO players(id, name, surname, hand, date, country) VALUES ('"+i+"', '"+a+"', '"+b+"', '"+c+"', '"+j+"', '"+d+"');";
		rf.instrukcja(sql);
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void showstats() throws SQLException {
		System.out.println("Wpisz id gracza");
    	i=takeoption();
		try {
		Connection conn = DriverManager.getConnection(DB_URL);
		sql="SELECT currentrank, name, surname, country, points FROM players LEFT JOIN rank ON players.id=rank.id WHERE players.id="+i+";";
		conn.prepareStatement(sql);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		DBTablePrinter.printResultSet(rs);
		}catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void removeplayer()
	{
	System.out.println("Wpisz id gracza do usuni�cia");
    i=takeoption();
	sql="DELETE FROM players WHERE id="+i+";";
	rf.instrukcja(sql);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void developermode() throws IOException {
	int x;
	String s;
	System.out.println("Wpisz has�o");
	s=takeoptiontext();
	if(!s.equals("admin")) 
		{
		System.out.println("B��dne has�o");
		return;
		}
	System.out.println("Wybierz opcj�");
	System.out.println("1. Komenda SELECT");
	System.out.println("2. Inna komenda j�zyka sqlite");
	x=takeoption();
	switch(x)
	{
	case 1:
	sql=takeoptiontext();
	try {
	Connection conn = DriverManager.getConnection(DB_URL);
	conn.prepareStatement(sql);
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	DBTablePrinter.printResultSet(rs);
	}catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}
	break;
	case 2:
	System.out.println("Wpisz komend�");
	s=takeoptiontext();
	rf.instrukcja(s);
	break;
	}
	}
	//--------------------------------------------------------------------------------------------------------------------------------	
	public void top1() throws SQLException {
    	//System.out.println("Ilu najlepszych graczy wypisa�?");
    	//i=takeoption();
		try {
		Connection conn = DriverManager.getConnection(DB_URL);
		sql="SELECT currentrank, name, surname, country, points FROM players LEFT JOIN rank ON players.id=rank.id WHERE currentrank="+10+" ORDER BY players.id ASC;";
		conn.prepareStatement(sql);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		DBTablePrinter.printResultSet(rs);
		}catch (SQLException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
