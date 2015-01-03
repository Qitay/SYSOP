package memory;
import java.util.Scanner;

public class Pamiec
{
	byte[] pamiec_op; 	//TABLICA PAMI�CI OPERACYJNEJ
	boolean [] wektor_zajetosci, bit_odniesienia;
	int rozmiar_ramki = 16, liczba_ramek;
		
	int adres_FIZYCZNY, indeks_strony;
	int offset, nr_ramki, nr_ramki2, ofiara;
	int indx;		//indeks strony do ktorej zapisujemy
	int numer_tablicy;
	byte[] bufor;
	
	byte tmp2;
	int liczba_stron;		//liczba stron danego procesu
	boolean znalezienie_ramki, znalezienie_ofiary;
	
	int i, j, k, l, ramka;		//zmienne pomocnicze
	String temporary, sprowadzana_strona;
	Indeks[] tablica_proc;
	Indeks tmp;
	
	Plik_wymiany plik_zew;
	Bity_odniesienia bb;
	
	public Pamiec()
	{
		bufor = new byte [16];
		
		liczba_ramek = 256/rozmiar_ramki;
		pamiec_op = new byte[256];
		wektor_zajetosci = new boolean[liczba_ramek];
		plik_zew = new Plik_wymiany();		//inicjalizacja pliku zewn�trznego
		bb = new Bity_odniesienia();
		tablica_proc = new Indeks[16];
		bit_odniesienia = new boolean[ liczba_ramek ];
				
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			wektor_zajetosci [ i ] = true;
			tablica_proc [ i ] = null;
			bit_odniesienia[ i ] = false; 
		}
	}		//konstruktor pami�ci

	public int ZAPIS( String kp )		//JAKO ARGUMENT STRING KTORY CHCESZ ZAPISAC
	{	
		liczba_stron = ( (kp.length()-1)/16 + 1 );
							//szukanie pustego indeksu w kt�rym zapiszemy tablice stron
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			if( tablica_proc [ i ] == null )
			{
				tablica_proc [ i ] = new Indeks(liczba_stron);
				break;
			}
		}
		indx = 0;	
		for( j = 0; j < liczba_stron ; j++ )
		{
			bufor = new byte[ 16 ];			//od�wie�enie bufora
			if( kp.length() > 16 )				//wyodr�bnienie strony z napisu - POCZ�TEK
			{
				temporary = kp.substring(0, 16);
				kp = kp.substring( 16 , kp.length() );
			}
			else
			{
				temporary = kp;
			}									//wyodr�bnienie strony z napisu - KONIEC
			
			bufor = temporary.getBytes();		//zamiana strony na bajty
			
			if( j==0 )
			{
				nr_ramki2 = znajdz_ramke();
				if( nr_ramki2 == -1 )
				{
					//stronicowanie
				}
				else if( nr_ramki2 > -1 )
				{
					zapis_do_ramki( bufor , i , j , nr_ramki2 );
				}
			}
			plik_zew.zapis_plik( bufor , i , indx );
			indx++;
		}
		return i;
	}
	
	public byte ODCZYT( int indeks, int adrs_log )		//JAKO ARGUMENTY NUMER PROCESU ORAZ ADRES POTRZEBNEGO BAJTU
	{
		indeks_strony = adrs_log/rozmiar_ramki;
		try
		{
			if( tablica_proc[ indeks ].waznosc[ indeks_strony ] == false)
			{
				System.out.println("Blad strony!!!");
				sprowadzana_strona = plik_zew.sprowadzenie_strony(indeks, indeks_strony);
				bufor = sprowadzana_strona.getBytes();
				nr_ramki2 = znajdz_ramke();
				if( nr_ramki2 == -1)
				{
					ofiara = bb.znajdz_ofiare();
					for( i = 0 ; i < rozmiar_ramki ; i++ )
					{
						pamiec_op[ rozmiar_ramki * ofiara + i ] = 0;
					}
					zapis_do_ramki( bufor , indeks , indeks_strony , ofiara );
					znalezienie_ofiary = false;
					for( i = 0 ; i < liczba_ramek ; i++ )
					{
						if( tablica_proc[ i ] != null )
						{
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
						if( znalezienie_ofiary==true) break;
					}
				}
				else if( nr_ramki >= 0)
				{
					zapis_do_ramki( bufor , indeks , indeks_strony , nr_ramki2 );
				}
				tablica_proc[ indeks ].waznosc[ indeks_strony ] = true;
			}
			ramka = przeliczanie_adresu( indeks, adrs_log );
			bit_odniesienia [ ramka/16 ] = true;
			bit_odniesienia = bb.sprawdz_wektor( bit_odniesienia );
			return pamiec_op[ ramka ];
		}
		catch( Exception e )
		{
			return -1;
		}
	}
	
	public int KLONUJ( int stary ) 	//JAKO ARGUMENT NUMER PROCESU DO SKLONOWANIA
	{
		temporary = "";
		for( i = 0 ; i < tablica_proc[ stary ].wielkosc; i++ )
		{
			temporary = temporary + plik_zew.sprowadzenie_strony(stary, i);
		}
		i = ZAPIS( temporary );
		return i;
	}
	
	public int NADPISZ( String kp , int proces )	//JAKO ARGUMENTY: NOWY KOD ORAZ NUMER PROCESU DO NADPISANIA
	{
		USUN( proces );
		i = ZAPIS( kp );
		return i;
	}
	
	public byte USUN( int indeks )		//JAKO ARGUMENT NUMER PROCESU DO USUNIECIA
	{
		try
		{
			for( i = 0 ; i < tablica_proc[ indeks ].wielkosc ; i++ )
			{
				if( tablica_proc[ indeks ].waznosc[ i ] == true)
				{
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
		indeks_strony = adrs_log/rozmiar_ramki;
		nr_ramki = tablica_proc[ indeks ].tablica_stron[ indeks_strony ];
		offset = adrs_log % rozmiar_ramki;
		adres_FIZYCZNY = (nr_ramki * rozmiar_ramki) + offset;
		return adres_FIZYCZNY;
	}
	
	int znajdz_ramke()
	{
		znalezienie_ramki = false;
		for( k = 0 ; k < liczba_ramek ; k++ )
		{
			if( wektor_zajetosci[ k ] == true )
			{
				znalezienie_ramki = true;
				wektor_zajetosci [ k ] = false;
				break;
			}
		}
		
		if( znalezienie_ramki == true )
		{
			nr_ramki2 = k;
		}
		else if( znalezienie_ramki == false )
		{
			nr_ramki2 = -1;
		}
		return nr_ramki2;
	}
	
	void zapis_do_ramki( byte[] tab , int indeks , int nr_strony , int nr)
	{
		for( l = 0 ; l < tab.length ; l++ )
		{
			pamiec_op[ rozmiar_ramki * nr + l ] = tab[ l ];
		}
		wektor_zajetosci [ nr ] = false;
		tablica_proc[ indeks ].tablica_stron[ nr_strony ] = nr;
		tablica_proc[ indeks ].waznosc[ nr_strony ] = true; 		//ustawienie bitu waznosci na valid
	}

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
}