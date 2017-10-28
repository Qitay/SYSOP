public class Bity_odniesienia
{
	MemoryManagement MM; 	
	static String[] dodatkowe_odniesienia;
	int dl;
	
	protected Bity_odniesienia()
	{
		int i;
		dodatkowe_odniesienia = new String [ 16 ];
		dl = 1;
		for( i = 0 ; i < 16 ; i++ )
		{
			dodatkowe_odniesienia[ i ] = "0";
		}
	}
	
	boolean[] sprawdz_wektor( boolean[] a )
	{
		if(dl<7)
		{
			dl++;
		}
		int i;
		boolean[] wyzerowana = a;
		for( i = 0; i < 16 ; i++ )
		{	
			if( dl == 7)
			{
				dodatkowe_odniesienia [ i ] = dodatkowe_odniesienia [ i ].substring(0,5);
			}
			
			if( a[ i ] == false )
			{
				dodatkowe_odniesienia [ i ] = "0" + dodatkowe_odniesienia [ i ];
			}
			else if( a[ i ] == true)
			{
				dodatkowe_odniesienia [ i ] = "1" + dodatkowe_odniesienia [ i ];
				wyzerowana[ i ] = false;
			}
		}
		return wyzerowana;
	}
	
	int znajdz_ofiare()
	{
		int ofiara = 0 , i , min = 127;
		for( i = 0; i < 16; i++ )
		{
			if( min > Integer.parseInt( dodatkowe_odniesienia [ i ] ))
			{
				min = Integer.parseInt( dodatkowe_odniesienia [ i ] );
				ofiara = i;
			}
		}
		return ofiara;
	}
}
