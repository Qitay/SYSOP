
public class UserManagement {

	ProcessMaganement PsM;
	Usr usr;
	Log log;
	Root root;
	//User user; // <-konstruktor przeszkadza
	
	UserManagement()
	{
		log = new Log();
		root = new Root();
		usr = new Usr();
		//user = new User();
		
		log.UM=this; 
		root.UM=this;
		usr.UM=this;
	}

	
	
}
