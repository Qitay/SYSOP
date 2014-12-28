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
	String tmp, tmp2, tmp3;		//Obiekty pomocnicze
	String[] linie_pliku;
	
	int i, j, potrzebna_linia;
	int[] b, c;		//tablica pomocnicza
	File plik;		//plik wymiany
	FileWriter zapis = null;
	FileReader odczyt = null;
	BufferedReader odczytywanie = null;
	
	List<int[]> lista;
	
	protected Plik_wymiany()
	{
		b = new int[ 2 ];
		lista = new LinkedList<int[]>();
		plik = new File ( "Plik_wymiany.txt" );
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
		return tmp;
	}
	
	void zapis_plik( byte[] bufor , int x , int y )
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
		dodaj_na_liste( x , y );
	}
	
	void dodaj_na_liste( int x , int y )
	{
		b = new int[ 2 ];
		b[ 0 ] = x;
		b[ 1 ] = y;
		lista.add( b );
	}
	
	String sprowadzenie_strony( int k , int j )
	{
		try
		{
			odczyt = new FileReader ( plik );
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		for( i = 0 ; i < lista.size() ; i++ )
		{
			b = new int[ 2 ];
			b = lista.get(i);
			if( b[0] == k && b[1] == j )
			{
				potrzebna_linia = i;
			}
		}
		i = 0;
		odczytywanie = new BufferedReader( odczyt );
		try 
		{
			if( potrzebna_linia!=0 )
			{
				while( odczytywanie.readLine() != null && i < potrzebna_linia - 1 )
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
	
	void usun_strone( int indeks , int nr_strony )
	{
		try
		{
			odczyt = new FileReader ( plik );
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		c = new int[ 2 ];
		b = new int[ 2 ];
		b[ 0 ] = indeks;
		b[ 1 ] = nr_strony;
		odczytywanie = new BufferedReader( odczyt );
		linie_pliku = new String[lista.size()];
		
		for( i = 0 ; i < lista.size() ; i++ )
		{			
			try 
			{
				linie_pliku [ i ] = odczytywanie.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			c = lista.get(i);
			if( c[0] == b[0] && c[1] == b[1] )
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
			odczyt.close();
			odczytywanie.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	void nadpisz_strone( byte[] bufor , int indeks , int nr_strony )
	{
		try
		{
			odczyt = new FileReader ( plik );
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		c = new int[ 2 ];
		b = new int[ 2 ];
		b[ 0 ] = indeks;
		b[ 1 ] = nr_strony;
		odczytywanie = new BufferedReader( odczyt );
		linie_pliku = new String[lista.size()];
		tmp3 = konwersja( bufor );
		
		for( i = 0 ; i < lista.size() ; i++ )
		{			
			try 
			{
				linie_pliku [ i ] = odczytywanie.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			c = lista.get(i);
			if( c[0] == b[0] && c[1] == b[1] )
			{	
				linie_pliku [ i ] = tmp3;
			}
		}
		
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
			odczyt.close();
			odczytywanie.close();
		} 
		catch (IOException e) 
		{
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
}