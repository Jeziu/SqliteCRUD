import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	ReadFile rf = new ReadFile();
	Funkcje fn = new Funkcje();
	int i, j;
	String s;
	
	public void printmenu() {
	
	        System.out.println(" Witaj u�ytkowniku, wybierz opcj� ");
	        System.out.println("1. Wczytaj/Uaktualnij baz�"); //dziala
	        System.out.println("2. Wy�wietl ca�� baz�"); //dziala
	        System.out.println("3. Wy�wietl top X graczy"); //dziala
	        System.out.println("4. Wy�wietl statystki gracza"); //dziala
	        System.out.println("5. Zmie� punkty graczowi");  //chyba
	        System.out.println("6. Dodaj punkty graczowi"); //chyba
	        System.out.println("7. Dodaj gracza"); //dziala
	        System.out.println("8. Usu� gracza"); //dziala
	        System.out.println("9. Tryb Developerski"); //dziala
	        System.out.println("10. Wyj�cie");
	        System.out.print("Wprowadz cyfr�>> ");
	
	}
	
	
	public void full() throws SQLException, IOException {
		int x=0;

		    while (x<1 || x>10) {
		    printmenu();
		    x = fn.takeoption();
		    clearScreen();
		    switch (x) {
		        case 1:
		        	fn.wczytaj();
		            break;
		        case 2:
		        	fn.wyswietl();
		            break;
		        case 3:
		        	fn.top();
		            break;
		        case 4:
		        	fn.showstats();
		        	break;
		        case 5:
		        	fn.changepoints();
		        	break;
		        case 6:
		        	fn.updatepoints();
		        	break;
		        case 7:
		        	fn.addplayer();
		        	break;
		        case 8:
		        	fn.removeplayer();
		        	break;
		        case 9:
		        	fn.developermode();
		        case 10:
		            break;
		        default:
		            System.out.println("Prosz� wybra� prawid�ow� opcj�");
		    }
		}
		System.out.println("Dzi�kujemy za skorzystanie z naszego programu");
		System.out.println("Czy chcesz skorzysta� ponownie?");
		System.out.println("Prosz� wcisn�� Y aby wr�ci� do menu, "
				+ "lub dowolny inny klawisz aby zako�czy� dzia�anie programu");
		s=fn.takeoptiontext();
		System.out.println(s);
		if(s.equals("Y") || s.equals("y")) full();
		else return;
	}
	
 
	public final static void clearScreen()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	}