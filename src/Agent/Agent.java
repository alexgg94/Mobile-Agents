package Agent;

import Server.Server_Interface;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Agent implements Agent_Interface, Serializable
{
 private String name;
 private Stack<String> nodes;
 private List<String> visited_nodes;
 
 public Agent(String name, Stack<String>nodes)
    {
     this.name = name;
     this.nodes = nodes;
     visited_nodes = new ArrayList();
    }
    
 @Override
 public List<String> execute() throws RemoteException, NotBoundException, MalformedURLException 
    {
     System.out.println("Hello! I'm Agent " + this.name);
     String tmp;   
     try
        {
         Server_Interface si = (Server_Interface)Naming.lookup(this.nodes.pop());
         addVisitedNode(si.getName());
         this.visited_nodes = si.receive(this);
        }
     
     catch(Exception ex)
        {  
         System.out.println("I'm coming back Home!!!");
        }
     
     return this.visited_nodes;
    }
    
 public String getName()
    {
     return this.name;
    }
 
 public void addVisitedNode(String visited_node)
    {
     this.visited_nodes.add(visited_node);
    }
 
 public List<String> getVisitedNodes()
    {
     return this.visited_nodes;
    }
}
