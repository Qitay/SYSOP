import filesystem.*;
import memory.*;
import pipes.*;
import procesor.*;
import processmanagement.*;
import users.*;

public class System {

	Pliki pliki;
	Pamiec pamiec;
	Pipe pipes;
	Klasa oli;
	public System()
	{
		pliki = new Pliki();
		pamiec = new Pamiec();
		pipes = new Pipe();
		oli = new Klasa();
	}
	public static void main(String[] args) {
		System sys = new System();
		
		///.....

	}

}
