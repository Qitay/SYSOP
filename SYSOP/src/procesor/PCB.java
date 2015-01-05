package procesor;

import procesor.PCB;
import procesor.rejestry;

public class PCB 
{
	public String idd;
	
	public Boolean nowy;
	public Boolean running;
	public Boolean waiting;
	public Boolean ready;
	public Boolean terminated;
	
	public int adress;
	
	public PCB NEXT_PCB_ALL;
    public PCB LAST_PCB_ALL;
    public PCB NEXT_PCB_GROUP;
    public PCB LAST_PCB_GROUP;
    
    public static Object[] cpu_stan = new Object[6];
    
    public int bazanice;
    public int pri;
    public int cpu;
    
    public PCB NEXT_SEMAPHORE_WAITER;
    
    public PCB(String id, int adres)
    {
    	idd=id;
    	adress=adres;
    	
    	nowy = true;
    	running = false;
    	waiting = false;
    	ready = false;
    	terminated = false;
    	
    	NEXT_PCB_ALL = null;
        LAST_PCB_ALL = null;
        NEXT_PCB_GROUP = null;
        LAST_PCB_GROUP = null;
        
        NEXT_SEMAPHORE_WAITER = null;
        
        bazanice = 60;
        cpu = 60;
        pri = bazanice + cpu/2;    	
    }
    
    public void zapisz_stan()
    {
    	cpu_stan[0] = rejestry.r0;
        cpu_stan[1] = rejestry.r1;
        cpu_stan[2] = rejestry.r2;
        cpu_stan[3] = rejestry.r3;
        cpu_stan[4] = rejestry.r4;
        cpu_stan[5] = rejestry.r5;
        cpu_stan[6] = rejestry.lr;
    }
    
    public void czytaj_stan()
    {
    	rejestry.r0 = cpu_stan[0];
    	rejestry.r1 = cpu_stan[1];
    	rejestry.r2 = cpu_stan[2];
    	rejestry.r3 = cpu_stan[3];
    	rejestry.r4 = cpu_stan[4];
    	rejestry.r5 = cpu_stan[5];
    	rejestry.lr = (int)cpu_stan[6];
    }   
}



