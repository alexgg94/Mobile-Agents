package Server;

import Agent.Agent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Server extends UnicastRemoteObject implements Server_Interface
{
 private String name;
 
 public Server() throws RemoteException {super();}
    
 @Override
 public List<String> receive(Agent agent) throws RemoteException, InterruptedException, NotBoundException, MalformedURLException
    {
     System.out.println("\n----------------------------------------------\n");   
     System.out.println("Welcome agent " + agent.getName() + " I'm the server " + this.name);
     TimeUnit.SECONDS.sleep(3);
     System.out.println("Nodes already visited: ");
     System.out.println(agent.getVisitedNodes());
     return agent.execute();
    }
    
 public void setName(String name)
    {
     this.name = name;
    }  
 
 @Override
 public String getName() throws RemoteException
    {
     return this.name;
    } 
}
