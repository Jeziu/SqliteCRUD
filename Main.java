//Autor: Maciej Jezierski
//Data wykonania: 14.11.2017
//Program pozwala na wykonanie dzia³añ na bazie danych zawieraj¹cej dane profesjonalnych tenisistów
//zrzeszonych w organizacji ATP oraz ich ranking z wybranych okresów.
//Program pozwala wyœwietlaæ rangê danego zawodnika, najlepszych zawodników, oraz modyfikowaæ bazy na wiele
//sposobów, domyœlnie zaimplementowane s¹ metody opieraj¹ce siê na komendach jêzyka sqlite takich jak:
//UPDATE, INSTERT, DELETE, oraz z³o¿enia tabel (LEFT JOIN). Zaimplementowana zosta³a tak¿e opcja deweloperska,
//zabezpieczona domyœlnie has³em "admin" która pozwala na wykonanie dolownej komendy jêzyka sqlite.

//Aby uruchomiæ program nale¿y dodaæ sterownik sqlite-jdbc-3.20.0.jar do œcie¿ki 
//budowania programu (plik jar zawarty w folderze z projektem).

//Bazy danych s¹ zawarte w g³ównym folderze projektu, mo¿na je tak¿e pobraæ z 
//https://github.com/JeffSackmann/tennis_atp, potrzebne bêd¹ tylko dwie bazy .csv, 
// atp_players.csv oraz atp_rankings_current.csv które nale¿y umieœciæ w g³ównym folderze projektu.

//Tabele zostan¹ stworzone i wype³nione oraz zapisane do bazy tenis.db przez program po wybraniu opcji nr 1 w menu.



import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		ReadFile rf = new ReadFile();
		Menu me = new Menu();
		rf.connect();
		me.full();

	}
	
}




