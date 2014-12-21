package memory;

public class Pamiec
{
	private byte[] pamiec_op; 	//TABLICA PAMIÊCI OPERACYJNEJ
	boolean [] wektor_zajetosci;
	int rozmiar_ramki = 16, liczba_ramek;
		
	int[] tablica_stron;	//TABLICA STRON
	int indx;			//indeks w tablicy stron
	int nr_strony;
	
	
	int adres_FIZYCZNY, adres_LOGICZNY;
	int offset, nr_ramki;
	
	
	byte[] bufor;		//bufor do czytania danych od proc
						//POTRZBNA JESZCZE JAKAS ZMIENNA 
						//USTAWIANA NA 1 GDY JEST ZAPIS I NA 0 GDY DANE JUZ ODCZYTANO
	
	int i, j;		//zmienne pomocnicze
	String tmp;
	
	
	Plik_wymiany plik_zew;
	
	public Pamiec()
	{
		indx = 0;
		
		liczba_ramek = 256/rozmiar_ramki;
		pamiec_op = new byte[256];
		wektor_zajetosci = new boolean[liczba_ramek];
		tablica_stron = new int[liczba_ramek];
		bufor = new byte[rozmiar_ramki];
		
		plik_zew = new Plik_wymiany();		//inicjalizacja pliku zewnêtrznego
		
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			wektor_zajetosci [ i ] = true;
			tablica_stron [ i ] = -1;
		}
	}		//konstruktor pamiêci

	int przeliczanie_adresu(int adres_log)
	{		
		nr_strony = (adres_log / rozmiar_ramki);
		offset = (adres_log % rozmiar_ramki);

		nr_ramki = tablica_stron[nr_strony];
		return (nr_ramki * rozmiar_ramki + offset);
	}

	byte odczyt(int adres_log)
	{
		adres_FIZYCZNY = przeliczanie_adresu(adres_log);
		return pamiec_op[adres_FIZYCZNY];
	}

	int zapis()
	{	
		plik_zew.zapis_plik(bufor);
		
		for( i = 0 ; i < liczba_ramek ; i++ )
		{
			if( wektor_zajetosci [ i ] == true )
			{				
				for( j = 0 ; j < rozmiar_ramki ; j++ )
				{
					pamiec_op [ rozmiar_ramki * i + j ] = bufor [ j ];
					bufor[ j ] = 0;			//odœwie¿anie bufora dla kolejnych danych które mog¹ wp³yn¹æ
				}
				wektor_zajetosci [ i ] = false;
				break;
			}
		}		
		plik_zew.dodaj_na_liste( i );
		znajdz_strone();
		tablica_stron [ indx ] = i;
		
		//miejsce na obliczanie dok³anego adresu logicznego
		//nie wiadomo gdzie dok³adnie znajd¹ sie zmienne dotycz¹ce komendy
		//
		
		return adres_LOGICZNY;
	}
	
	void znajdz_strone()
	{
		for( j = 0; j < liczba_ramek; j++)
		{
			if( tablica_stron [ j ] == -1)
			{
				indx = j;
				break;
			}
		}
	}		//przypisuje zmiennej indx nr indeksu znalezione strony
		
	void usun(int strona)
	{
		nr_ramki = tablica_stron [strona];
		tablica_stron [ strona ] = -1;
		for( i = 0 ; i < rozmiar_ramki ; i++ )
		{
			pamiec_op[ ( nr_ramki*rozmiar_ramki + i ) ] = 0;
		}
		wektor_zajetosci [ strona ] = true;
		plik_zew.usun_strone(strona);
	}
	
	String odczyt_z_pliku( int k )
	{
		return plik_zew.odczyt( k );
	}
	
	public static void main(String[] args)
	{
		Pamiec a = new Pamiec();
			
		
		byte[] n=new byte[16];
		String kupa="MOV 2 , 4";
		n=kupa.getBytes();
		for(int k=0;k<kupa.length();k++)
		{
			a.bufor[k]=n[k];
		}
		
		
		
		a.zapis();
		for(int k=0;k<256;k++)
		{
			System.out.print(a.pamiec_op[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.tablica_stron[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.wektor_zajetosci[k]+" ");
		}
		System.out.println();
		
		
		
		kupa="MVI 2 , 4";
		n=kupa.getBytes();
		for(int k=0;k<kupa.length();k++)
		{
			a.bufor[k]=n[k];
		}
		
		
		
		a.zapis();
		for(int k=0;k<256;k++)
		{
			System.out.print(a.pamiec_op[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.tablica_stron[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.wektor_zajetosci[k]+" ");
		}
		System.out.println();
		
		
		kupa="SUB 3,1";
		n=kupa.getBytes();
		for(int k=0;k<kupa.length();k++)
		{
			a.bufor[k]=n[k];
		}
		
		a.zapis();
		for(int k=0;k<256;k++)
		{
			System.out.print(a.pamiec_op[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.tablica_stron[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.wektor_zajetosci[k]+" ");
		}
		System.out.println();
		
		a.usun(0);
		for(int k=0;k<256;k++)
		{
			System.out.print(a.pamiec_op[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.tablica_stron[k]+" ");
		}
		System.out.println();
		for(int k=0;k<a.rozmiar_ramki;k++)
		{
			System.out.print(a.wektor_zajetosci[k]+" ");
		}
		System.out.println();
		
		a.plik_zew.wyswietl();
	}
}