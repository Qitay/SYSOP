package memory;

public class Indeks
{
	int[] tablica_stron;
	int i;
	boolean[] waznosc;
	int wielkosc;
	public Indeks( int n )
	{
		wielkosc = n;
		tablica_stron = new int[ n ];
		waznosc = new boolean[ n ];
		for( i = 0 ; i < n ; i++ )
		{
			waznosc[ i ] = false;		//ustawienie dla wszystkich stron bitu INVALID
			tablica_stron[ i ] = -1;
		}
	}
}
