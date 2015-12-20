package Main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

public interface Itinerary_Database_Interface extends Remote
{
 public void addNode(String node) throws RemoteException;
 public Stack<String> getItinerary() throws RemoteException;
 public void close() throws RemoteException;
 public boolean getSate() throws RemoteException; 
}
