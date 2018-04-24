//Autor: Maciej Jezierski
//Data wykonania: 14.11.2017
//Program pozwala na wykonanie dzia�a� na bazie danych zawieraj�cej dane profesjonalnych tenisist�w
//zrzeszonych w organizacji ATP oraz ich ranking z wybranych okres�w.
//Program pozwala wy�wietla� rang� danego zawodnika, najlepszych zawodnik�w, oraz modyfikowa� bazy na wiele
//sposob�w, domy�lnie zaimplementowane s� metody opieraj�ce si� na komendach j�zyka sqlite takich jak:
//UPDATE, INSTERT, DELETE, oraz z�o�enia tabel (LEFT JOIN). Zaimplementowana zosta�a tak�e opcja deweloperska,
//zabezpieczona domy�lnie has�em "admin" kt�ra pozwala na wykonanie dolownej komendy j�zyka sqlite.

//Aby uruchomi� program nale�y doda� sterownik sqlite-jdbc-3.20.0.jar do �cie�ki 
//budowania programu (plik jar zawarty w folderze z projektem).

//Bazy danych s� zawarte w g��wnym folderze projektu, mo�na je tak�e pobra� z 
//https://github.com/JeffSackmann/tennis_atp, potrzebne b�d� tylko dwie bazy .csv, 
// atp_players.csv oraz atp_rankings_current.csv kt�re nale�y umie�ci� w g��wnym folderze projektu.

//Tabele zostan� stworzone i wype�nione oraz zapisane do bazy tenis.db przez program po wybraniu opcji nr 1 w menu.



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




