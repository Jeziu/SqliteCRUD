import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadFile {
	
	public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:tenis.db";
    
    private static Connection conn;
    private static Statement stmt;
	
    String sql;
    
	public void connect()
	{
	
		try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
	    
        //nawiazuje polaczenie z baza danych
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otworzeniem po³¹czenia");
            e.printStackTrace();
        }
	}
	
//--------------------------------------------------------------------------------------------------------------------------------

	
	public void create() {
        //tworze tabele
        String createOsoba="CREATE TABLE IF NOT EXISTS players(id INTEGER PRIMARY KEY, name VARCHAR(30), surname VARCHAR(30), hand VARCHAR(1), date INTEGRER, country VARCHAR(3))";
        try {
            stmt.execute(createOsoba);  
        } catch (SQLException e) {
            System.err.println("B³¹d przy tworzeniu tabeli");
            e.printStackTrace();
        }
        
        String createOsoba1="CREATE TABLE IF NOT EXISTS rank(date INTEGER, currentrank int, id INTEGRER FOREGIN KEY REFERENCES players(id), points VARCHAR(3))";
        try {
            stmt.execute(createOsoba1);
        } catch (SQLException e) {
            System.err.println("B³¹d przy tworzeniu tabeli1");
            e.printStackTrace();
        }
	}
	
//--------------------------------------------------------------------------------------------------------------------------------
	
		public void read() {
		try {
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader ("atp_players.csv")); 
				conn.setAutoCommit(false);
				String s = br.readLine();
				int i;
	        	sql="INSERT INTO players(id, name, surname, hand, date, country) VALUES (?, ?, ?, ?, ?, ?);";
				PreparedStatement ps = conn.prepareStatement(sql);
				while (s != null)
				{
					s+=" ";
					String[] sta = s.split(","); 
					ps.setString(1, sta[0]);
					ps.setString(2, sta[1]);
					ps.setString(3, sta[2]);
					ps.setString(4, sta[3]);
					ps.setString(5, sta[4]);
					ps.setString(6, sta[5]);
					ps.addBatch();
		        	i=Integer.parseInt(sta[0]);
		        	i-=100000;
					System.out.println("Wczytano rekord numerd id równym "+ i + " do encji players");
					s = br.readLine();
				}
				ps.executeBatch();
				conn.setAutoCommit(true);
			} catch (IOException e)
			{
				System.out.println("B³¹d odczytu danych");
				e.printStackTrace();
			}
		}catch(SQLException x) {
			x.printStackTrace();
		}
	    
	try {
		try 
			{
				BufferedReader br = new BufferedReader(new FileReader ("atp_rankings_current.csv")); 
				conn.setAutoCommit(false);
				String s = br.readLine();
				int i;
		    	sql="INSERT INTO rank(date, currentrank, id, points) VALUES ( ?, ?, ?, ?);";	        	
				PreparedStatement ps = conn.prepareStatement(sql);
				while (s != null)
				{
					s+=" ";
					String[] sta = s.split(","); 
					ps.setString(1, sta[0]);
					ps.setString(2, sta[1]);
					ps.setString(3, sta[2]);
					ps.setString(4, sta[3]);
					ps.addBatch();
		        	i=Integer.parseInt(sta[2]);
		        	i-=100000;
					System.out.println("Wczytano rekord numerd id równym "+ i + " do encji rank");
					s = br.readLine();
				}
				ps.executeBatch();
				conn.setAutoCommit(true); 
			}catch (IOException e)
			{
				System.out.println("B³¹d odczytu danych1");
			}
		} catch(SQLException x) {
			x.printStackTrace();
		}

	}
		
		

		public void instrukcja (String q) {
        	try {
				stmt.execute(q);
			} catch (SQLException e) {
				System.err.println("B³¹d przy wykonaniu");
				e.printStackTrace();
			}
		}
		
		

}
