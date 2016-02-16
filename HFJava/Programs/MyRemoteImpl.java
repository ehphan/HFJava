import java.rmi.*;
import java.rmi.server.*;
//UnicastRemoteObject is in the java.rmi.server package

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	public String sayHello() {
		return "Server says, 'eyylmao'";
	}

	public MyRemoteImpl() throws RemoteException{}
	//Your superclass constructor (for UnicastRemoteObject) declares an exception, so YOU must write a constructor, because it means that your constructor is calling risky code (its super)

	public static void main (String[] args) {

		try {
			MyRemote service = new MyRemoteImpl();
			//Make the remote object.
			Naming.rebind("Remote Hello", service);
			//Bind it to the rmiregistry using the static Naming.rebind(). The name you register it under is the name clients will need to look it up in the rmi registry
		}	catch (Exception ex) {ex.printStackTrace();}
	}
}