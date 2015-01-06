package procesor;
import java.io.UnsupportedEncodingException;
import processmanagement.ProcesorManagement;
import memory.Pamiec;


public class interpreter_rozkazow1 
{
	ProcesorManagement PrM;
	
	static String rozkaz, argumenty;
	static String[] tablica_arg = new String[ 2 ];
	static int end, arg, arg1;
	static Pamiec pam;
	static boolean isTrue, isTrue1;
	static byte[] tab = new byte[11];
		
	public static int petla(int indeks , int numer_bajtu)
	{
		int j;
		for( j = 0 ; j < 9 ; j++ )
		{
			tab[j] = pam.ODCZYT(indeks, numer_bajtu);
			if(tab[j] == 59) break;
		}
		try {
			rozkaz = new String(tab, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return j+1;
	}
	
	
	
	public static int Run(int indeks_procesu, int numer_bajtu) 
	{	
		argumenty = rozkaz.substring( 4 );
		tablica_arg = argumenty.split(" ");
		try
		{
			arg = Integer.parseInt(tablica_arg[0]);
		}
		catch(Exception e)
		{
			isTrue = true;
		}
		
		try
		{
			arg1 = Integer.parseInt(tablica_arg[1]);
		}
		catch(Exception e)
		{
			isTrue1 = true;
		}
		
		end = petla( indeks_procesu, numer_bajtu );
		if (rozkaz.substring( 0 , 3 ) == "MOV")
		{
			rejestry.lr++;
			if (tablica_arg[0] == "R0")
			{
				rejestry.lr++;
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.r0 = arg1;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.r5;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r0 = rejestry.lr;
				}
			}
			
			else if (tablica_arg[0] == "R1")
			{
				rejestry.lr++;
				
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.r1 = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.r5;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r1 = rejestry.lr;
				}		
			}
			
			else if (tablica_arg[0] == "R2")
			{
				rejestry.lr++;
				
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.r2 = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.r5;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r2 = rejestry.lr;
				}
				
			}
			
			else if (tablica_arg[0] == "R3")
			{
				rejestry.lr++;
				
				if (isTrue1 == false )
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.r3 = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.r5;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r3 = rejestry.lr;
				}
				
			}
			
			else if (tablica_arg[0] == "R4")
			{
				rejestry.lr++;
				
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.r4 = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.r5;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r4 = rejestry.lr;
				}
				
			}
			
			else if (tablica_arg[0] == "R5")
			{
				rejestry.lr++;
				
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.r5 = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
					rejestry.r5 = rejestry.lr;
				}
				
			}
			
			else if (tablica_arg[0] == "LR")
			{
				rejestry.lr++;
				
				if (isTrue1 == false)
				{
					rejestry.lr++;
					rejestry.lr++;
					rejestry.lr = arg1;
				}
				
				else if (tablica_arg[1] == "R0")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r0;
				}
				
				else if (tablica_arg[1] == "R1")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r1;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r2;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r3;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r4;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
					rejestry.lr = rejestry.r5;
				}
				
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "ADD")
		{
			rejestry.lr++;
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5+=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr+=arg1;
		            rejestry.lr++;
				}
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "SUB")
		{
			rejestry.lr++;
			
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5-=arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr-=arg1;
		            rejestry.lr++;
				}
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "MUL")
		{
			rejestry.lr++;
			
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0=rejestry.r0*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1=rejestry.r1*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2=rejestry.r2*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3=rejestry.r3*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4=rejestry.r4*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5=rejestry.r5*arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr=rejestry.lr*arg1;
		            rejestry.lr++;
				}
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "DIV")
		{
			rejestry.lr++;
			
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0=rejestry.r0/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1=rejestry.r1/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2=rejestry.r2/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3=rejestry.r3/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4=rejestry.r4/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5=rejestry.r5/arg1;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr=rejestry.lr/arg1;
		            rejestry.lr++;
				}
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "INC")
		{
			rejestry.lr++;
					
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5++;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr++;
				}
			}
		}
		
		else if (rozkaz.substring( 0 , 3 ) == "DEC")
		{
			rejestry.lr++;
			
			if (isTrue1 == false)
			{
				if (tablica_arg[0] == "R0")
				{		
		            rejestry.lr++;
		            rejestry.r0--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[0] == "R1")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r1--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R2")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r2--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R3")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r3--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R4")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r4--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "R5")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.r5--;
		            rejestry.lr++;
				}
				
				else if (tablica_arg[1] == "LR")
				{
					rejestry.lr++;
		            rejestry.lr++;
		            rejestry.lr--;
		            rejestry.lr++;
				}
			}
		}
		return end;
	}
}
