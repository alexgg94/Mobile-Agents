package Agent;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface Agent_Interface 
{    
 public List<String> execute()  throws RemoteException, NotBoundException, MalformedURLException;
}
