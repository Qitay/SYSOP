import java.util.Scanner;

public class Pamiec
{
	MemoryManagement MM;
	
	static byte[] pamiec_op; 	//TABLICA PAMIﾊCI OPERACYJNEJ
	static boolean [] wektor_zajetosci, bit_odniesienia;
	int rozmiar_ramki = 16, liczba_ramek;	
	static Indeks[] tablica_proc;
	Indeks tmp;
	
	Plik_wymiany plik_zew;
	Bity_odniesienia bb;
	
	public Pamiec()
	{		
		liczba_ramek = 256/rozmiar_ramki;
		pamiec_op = new byte[256];
		wektor_zajetosci = new boolean[liczba_ramek];
		plik_zew = new Plik_wymiany();		//inicjalizacja pliku zewn黎rznego
		bb = new Bity_odniesienia();
		tablica_proc = new Indeks[16];
		bit_odniesienia = new boolean[ liczba_ramek ];
		int i;
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			wektor_zajetosci [ i ] = true;
			tablica_proc [ i ] = null;
			bit_odniesienia[ i ] = false; 
		}
	}		//konstruktor pami鹹i

	public int ZAPIS( String kp )		//JAKO ARGUMENT STRING KTORY CHCESZ ZAPISAC
	{	
		String temporary;
		byte[] bufor;
		int liczba_stron = ( (kp.length()-1)/16 + 1 );
							//szukanie pustego indeksu w ktym zapiszemy tablice stron
		int i;
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			if( tablica_proc [ i ] == null )
			{
				tablica_proc [ i ] = new Indeks(liczba_stron);
				break;
			}
		}
		int indx = 0;
		int j;
		for( j = 0; j < liczba_stron ; j++ )
		{
			if( kp.length() > 16 )				//wyodr鹵nienie strony z napisu - POCZ･TEK
			{
				temporary = kp.substring(0, 16);
				kp = kp.substring( 16 , kp.length() );
			}
			else
			{
				temporary = kp;
			}									//wyodr鹵nienie strony z napisu - KONIEC
			
			bufor = temporary.getBytes();		//zamiana strony na bajty
			if( j==0 )
			{
				int nr_ramki = znajdz_ramke();
				if( nr_ramki == -1 )
				{
					ofiara( i , 0 , temporary );
				}
				else if( nr_ramki > -1 )
				{
					zapis_do_ramki( bufor , i , j , nr_ramki );
				}
			}
			plik_zew.zapis_plik( bufor , i , indx );
			indx++;
		}
		return i;
	}
	
	public byte ODCZYT( int indeks, int adrs_log )		//JAKO ARGUMENTY NUMER PROCESU ORAZ ADRES POTRZEBNEGO BAJTU
	{
		String sprowadzana_strona;
		int indeks_strony = adrs_log/rozmiar_ramki;
		byte[] bufor;
		try
		{
			if( tablica_proc[ indeks ].waznosc[ indeks_strony ] == false)
			{ 
				System.out.println("Blad strony!!!");
				sprowadzana_strona = plik_zew.sprowadzenie_strony(indeks, indeks_strony);
				int nr_ramki = znajdz_ramke();
				System.out.println("Sprowadzona strona: "+sprowadzana_strona+" Numer ramki: "+nr_ramki);
				if( nr_ramki == -1)
				{
					ofiara ( indeks , indeks_strony , sprowadzana_strona );
				}
				else if( nr_ramki >= 0)
				{
					bufor = sprowadzana_strona.getBytes();
					zapis_do_ramki( bufor , indeks , indeks_strony , nr_ramki );
				}
				tablica_proc[ indeks ].waznosc[ indeks_strony ] = true;
			}
			int ramka = przeliczanie_adresu( indeks, adrs_log );
			bit_odniesienia [ ramka/16 ] = true;
			bit_odniesienia = bb.sprawdz_wektor( bit_odniesienia );
			return pamiec_op[ ramka ];
		}
		catch( Exception e )
		{
			return -1;
		}
	}
	
	void ofiara( int indeks , int strona , String zawartosc )
	{
		int ofiara = bb.znajdz_ofiare();
		byte[] bufor = new byte[16];
		boolean znalezienie_ofiary = false;
		int i;
		for( i = 0 ; i < rozmiar_ramki ; i++ )
		{
			bufor[ i ] = pamiec_op[ rozmiar_ramki * ofiara + i ];
			pamiec_op[ rozmiar_ramki * ofiara + i ] = 0;
		}
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			if( tablica_proc[ i ] != null )
			{
				int j;
				for( j = 0 ; j < tablica_proc[ i ].wielkosc ; j++ )
				{
					if( tablica_proc[ i ].tablica_stron[ j ] == ofiara )
					{
						tablica_proc[ i ].tablica_stron[ j ] = -1;
						tablica_proc[ i ].waznosc[ j ] = false;
						plik_zew.nadpisz_strone( bufor, i , j );
						znalezienie_ofiary = true;
						break;
					}
				}
			}
			if( znalezienie_ofiary == true ) break;
		}
		bufor = zawartosc.getBytes();
		zapis_do_ramki( bufor , indeks , strona , ofiara );
	}
	
	public int KLONUJ( int stary ) 	//JAKO ARGUMENT NUMER PROCESU DO SKLONOWANIA
	{
		String temporary;
		if( tablica_proc[ stary ] == null )
		{
			return -1;
		}
		else
		{
			temporary = "";
			int i;
			for( i = 0 ; i < tablica_proc[ stary ].wielkosc; i++ )
			{
				temporary = temporary + plik_zew.sprowadzenie_strony(stary, i);
			}
			int q = ZAPIS( temporary );	
			return q;
		}
	}
	
	public int NADPISZ( String kp , int proces )	//JAKO ARGUMENTY: NOWY KOD ORAZ NUMER PROCESU DO NADPISANIA
	{
		int i;
		i = USUN( proces );
		i = ZAPIS( kp );
		return i;
	}
	
	public byte USUN( int indeks )		//JAKO ARGUMENT NUMER PROCESU DO USUNIECIA
	{
		try
		{
			int i;
			for( i = 0 ; i < tablica_proc[ indeks ].wielkosc ; i++ )
			{
				if( tablica_proc[ indeks ].waznosc[ i ] == true)
				{
					int j;
					for( j = 0 ; j < rozmiar_ramki ; j++ )
					{
						pamiec_op[ tablica_proc[ indeks ].tablica_stron[ i ]*16 + j ] = 0;
					}
					wektor_zajetosci [ tablica_proc[ indeks ].tablica_stron[ i ] ] = true;
				}
				plik_zew.usun_strone( indeks, i );
			}
			tablica_proc[ indeks ] = null;
			return 0;
		}
		catch( Exception e)
		{
			return -1;
		}	
	}
	
	int przeliczanie_adresu( int indeks, int adrs_log)
	{
		int indeks_strony = adrs_log/rozmiar_ramki;
		int offset = adrs_log % rozmiar_ramki;
		int adres_FIZYCZNY = (tablica_proc[ indeks ].tablica_stron[ indeks_strony ] * rozmiar_ramki) + offset;
		return adres_FIZYCZNY;
	}
	
	int znajdz_ramke()
	{
		int k;
		boolean znalezienie_ramki = false;
		for( k = 0 ; k < liczba_ramek ; k++ )
		{
			if( wektor_zajetosci[ k ] == true )
			{
				znalezienie_ramki = true;
				wektor_zajetosci [ k ] = false;
				break;
			}
		}
		int nr_ramki = 0;
		if( znalezienie_ramki == true )
		{
			nr_ramki = k;
		}
		else if( znalezienie_ramki == false )
		{
			nr_ramki = -1;
		}
		return nr_ramki;
	}
	
	void zapis_do_ramki( byte[] tab , int indeks , int nr_strony , int nr)
	{
		int l;
		for( l = 0 ; l < tab.length ; l++ )
		{
			pamiec_op[ rozmiar_ramki * nr + l ] = tab[ l ];
		}
		wektor_zajetosci [ nr ] = false;
		tablica_proc[ indeks ].tablica_stron[ nr_strony ] = nr;
		tablica_proc[ indeks ].waznosc[ nr_strony ] = true; 		//ustawienie bitu waznosci na valid
	}
/*
	public static void main(String[] args)
	{
		Scanner reading = new Scanner(System.in);
		Pamiec a = new Pamiec();
		int opcja, indeks_procesu, adres;
		String napis;
		int w;
		byte kupa;
		boolean poprawnosc = true;
		System.out.println("Pamiec operacyjna");
		System.out.println("1. Zapis");
		System.out.println("2. Odczyt");
		System.out.println("3. Usun proces");
		System.out.println("4. Wyswietl pamiec");
		System.out.println("5. Wyswietl zawartosc tablicy stron procesu");
		System.out.println("6. Wyswietl wektor zajetosci");
		System.out.println("7. Sklonuj kod");
		System.out.println("8. Nadpisz kod");
		System.out.println("9. Wyjscie");
		while( poprawnosc == true ){
			
			opcja = reading.nextInt();
			reading.nextLine();
			switch( opcja )
			{
				case 1:
				{
					System.out.println("Podaj dane do zapisu: ");
					napis = reading.nextLine();
					a.ZAPIS(napis);
					System.out.println("Dane zapisano");
					break;
				}
				case 2:
				{
					System.out.println("Podaj indeks procesu: ");
					indeks_procesu = reading.nextInt();
					System.out.println("Podaj adres: ");
					adres = reading.nextInt();
					kupa = a.ODCZYT( indeks_procesu, adres );
					if( kupa == -1 ) System.out.println("Podano zly indeks lub adres!!");
					else System.out.println( kupa );
					break;
				}
				case 3:
				{
					System.out.println("Podaj indeks procesu do usuniecia: ");
					indeks_procesu = reading.nextInt();
					kupa = a.USUN(indeks_procesu);
					if( kupa == -1) System.out.println("Podano zly indeks!!");
					else System.out.println("Usunieto proces");
					break;
				}
				case 4:
				{
					for( w = 0 ; w < 256 ; w++ )
					{
						if( w%16 == 0 )
						{
							System.out.print( "| " );
						}
						System.out.print( a.pamiec_op[ w ] + " " );
					}
					System.out.println();
					break;
				}
				case 5:
				{
					System.out.println("Podaj numer procesu: ");
					indeks_procesu = reading.nextInt();
					try{
						for( w = 0 ; w < a.tablica_proc[ indeks_procesu ].wielkosc ; w++ )
						{
							System.out.print( a.tablica_proc[ indeks_procesu ].tablica_stron[ w ] + " ");
						}
						System.out.println();
					}
					catch( Exception e )
					{
						System.out.println("Podano nieprawidlowy numer procesu");
					}
					break;
				}
				case 6:
				{
					for( w = 0 ; w < 16 ; w++ )
					{
						System.out.print(a.wektor_zajetosci[ w ] + " ");
					}
					System.out.println();
					break;
				}
				case 7:
				{
					System.out.println("Podaj indeks procesu: ");
					indeks_procesu = reading.nextInt();
					adres = a.KLONUJ( indeks_procesu );
					System.out.println("Klon zapisano pod adresem " + adres );
					break;
				}
				case 8:
				{
					System.out.println("Podaj dane do nadpisania: ");
					napis = reading.nextLine();
					System.out.println("Podaj indeks procesu: ");
					indeks_procesu = reading.nextInt();
					System.out.println("Dane nadpisano pod adresem"	+ a.NADPISZ( napis , indeks_procesu ));
					break;
				}
				case 9:
				{
					poprawnosc = false;
					break;
				}
				default:
				{
					System.out.println("Nieprawidlowy numer!!");
					break;
				}
			}
		}
		reading.close();
	}
	*/
}