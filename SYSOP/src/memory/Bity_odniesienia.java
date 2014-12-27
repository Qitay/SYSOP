package memory;

import java.util.Random;
import java.util.Scanner;


public class Bity_odniesienia
{
	String[] dodatkowe_odniesienia;
	int dl, i, ofiara, min;
	String bit;
	boolean[] wyzerowana; 
	
	protected Bity_odniesienia()
	{
		dodatkowe_odniesienia = new String [ 16 ];
		dl = 1;
	}
	
	boolean[] sprawdz_wektor( boolean[] a )
	{
		if(dl<7)
		{
			dl++;
		}
		wyzerowana = a;
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
		ofiara = 0;
		min = 57;
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

	public static void main(String[] args)
	{
		
	}
}
