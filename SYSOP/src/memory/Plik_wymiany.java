package memory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class Plik_wymiany
{
	String tmp, tmp2;		//Obiekt pomocniczy
	String[] linie_pliku;
	
	int i, j;
	File plik;		//plik wymiany
	FileWriter zapis = null;
	FileReader odczyt = null;
	BufferedReader odczytywanie = null;
	
	List<Integer> lista;
	
	protected Plik_wymiany()
	{
		lista = new LinkedList<Integer>();
		plik = new File ( "Plik_wymiany.txt" );
		try
		{
			odczyt = new FileReader ( plik );
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	String konwersja( byte[] bufor )
	{
		try 
		{
			tmp = new String(bufor, "UTF-8");	//konwersja byte -> String
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		tmp = tmp.substring ( 0,9 );
		return tmp;
	}
	
	void zapis_plik( byte[] bufor )
	{
		tmp2 = konwersja(bufor);

		try 
		{
			zapis = new FileWriter ( plik , true );
			zapis.write(tmp2+"\r\n");
			zapis.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	void dodaj_na_liste( int j )
	{
		lista.add( j );
	}
	
	String odczyt( int k )
	{
		i = 0;
		odczytywanie = new BufferedReader( odczyt );
		try 
		{
			if( k!=0 )
			{
				while( odczytywanie.readLine() != null && i < k - 1 )
				{
					i++;
				}
			}
			tmp = odczytywanie.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tmp;
	}
	
	void usun_strone( int k )
	{
		odczytywanie = new BufferedReader( odczyt );
		linie_pliku = new String[lista.size()];
		
		for(i=0;i<lista.size();i++)
		{			
			try 
			{
				linie_pliku [ i ] = odczytywanie.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			if( i==k )
			{	
				linie_pliku [ i ] = null;
				lista.remove(i);
				break;
			}
		}
		
		for( j = i; j < lista.size(); j++ )
		{
			try 
			{
				linie_pliku [ j ] = odczytywanie.readLine();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		/*for( i = 0 ; i < lista.size(); i++ )
		{
			System.out.print(linie_pliku [ i ]+ " ");
			System.out.println(lista.get(i));
		}*/
		
		
		
		try
		{
			zapis = new FileWriter ( plik );
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	
		plik.delete();
		
		try
		{
			zapis = new FileWriter ( plik , true );
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		for( i = 0 ; i < lista.size() ; i++ )
		{
			try 
			{
				zapis.write( linie_pliku [ i ] + "\r\n" );
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		try {
			zapis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void wyswietl()
	{
		for(i=0;i<lista.size(); i++)
		{
			System.out.print(lista.get(i)+" ");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}