package Server;

import Agent.Agent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Server_Interface extends Remote
{    
 public List<String> receive(Agent agent) throws RemoteException, InterruptedException, NotBoundException, MalformedURLException;
 public String getName() throws RemoteException;
}
