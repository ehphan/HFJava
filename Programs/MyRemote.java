import java.rmi.*;
//RemoteException and Remote interface are in java.rmi package

public interface MyRemote extends Remote {
	public String sayHello() throws RemoteException;
	//All of your remote methods must declare a RemoteException
}