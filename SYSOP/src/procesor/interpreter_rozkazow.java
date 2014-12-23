package procesor;
import memory.Pamiec;

public class interpreter_rozkazow 
{
	public static enum rozkaz
	{
		MOV, ADD, SUB, MUL, DIV, INC, DEC
	}; 
	
	public static enum wartosc
	{
		R0, R1, R2, R3, R4, R5, LR, WAR
	};
	
	
	public static void Run() 
	{
		if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.MOV.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				
			}
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.ADD.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{		
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r0=(int)rejestry.r0+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r1=(int)rejestry.r1+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r2=(int)rejestry.r2+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r3=(int)rejestry.r3+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r4=(int)rejestry.r4+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r5=(int)rejestry.r5+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.lr=(int)rejestry.lr+(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.SUB.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{		
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r0=(int)rejestry.r0-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r1=(int)rejestry.r1-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r2=(int)rejestry.r2-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r3=(int)rejestry.r3-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r4=(int)rejestry.r4-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r5=(int)rejestry.r5-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.lr=(int)rejestry.lr-(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.MUL.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{		
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r0=(int)rejestry.r0*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r1=(int)rejestry.r1*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r2=(int)rejestry.r2*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r3=(int)rejestry.r3*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r4=(int)rejestry.r4*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r5=(int)rejestry.r5*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.lr=(int)rejestry.lr*(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.DIV.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{		
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r0=(int)rejestry.r0/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r1=(int)rejestry.r1/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r2=(int)rejestry.r2/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r3=(int)rejestry.r3/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r4=(int)rejestry.r4/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.r5=(int)rejestry.r5/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
			else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				
				if(Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.WAR.ordinal())
				{
	            	rejestry.lr++;
	            	rejestry.lr=(int)rejestry.lr/(int)Pamiec.pamiec_op[(int)rejestry.lr];
	            	rejestry.lr++;
	            }
			}
			
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.INC.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r0;
				temp++;
				rejestry.r0 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r1;
				temp++;
				rejestry.r1 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r2;
				temp++;
				rejestry.r2 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r3;
				temp++;
				rejestry.r3 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r4;
				temp++;
				rejestry.r4 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r5;
				temp++;
				rejestry.r5 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.lr;
				temp++;
				rejestry.lr = temp;
			}
		}
		
		else if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)rozkaz.DEC.ordinal())
		{
			rejestry.lr++;
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R0.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r0;
				temp--;
				rejestry.r0 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R1.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r1;
				temp--;
				rejestry.r1 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R2.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r2;
				temp--;
				rejestry.r2 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R3.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r3;
				temp--;
				rejestry.r3 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R4.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r4;
				temp--;
				rejestry.r4 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.R5.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.r5;
				temp--;
				rejestry.r5 = temp;
			}
			
			if (Pamiec.pamiec_op[(int)rejestry.lr] == (byte)wartosc.LR.ordinal())
			{
				rejestry.lr++;
				int temp = (int)rejestry.lr;
				temp--;
				rejestry.lr = temp;
			}
		}
	}

}
