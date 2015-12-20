package Main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class Itinerary_Database extends UnicastRemoteObject implements Itinerary_Database_Interface
{
 private Stack<String> itinerary;
 private boolean state;
    
 public Itinerary_Database() throws RemoteException 
    {
     super(); 
     this.itinerary = new Stack();
     state = false;
    }

 @Override
 public void addNode(String node) throws RemoteException 
    {
     System.out.println("New node was added on the itinerary\t" + node);   
     this.itinerary.push(node);
    }

 @Override
 public Stack<String> getItinerary() throws RemoteException 
    {
     return this.itinerary;
    }

 @Override
 public void close() throws RemoteException 
    {
     this.state = true;
    } 
 
 @Override
 public boolean getSate() throws RemoteException 
    {
     return this.state;
    }
}
