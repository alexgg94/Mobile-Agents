package Agent;

import Main.Itinerary_Database_Interface;
import Server.Server_Interface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Client 
{
 private static String name;
 private static String itinerary_type;
 private static Stack<String> nodes;
 
 public static void main(String args[]) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException
    { 
     Itinerary_Database_Interface it = (Itinerary_Database_Interface)Naming.lookup("rmi://localhost:5000/main");
     
     Scanner scanIn = new Scanner(System.in);
      
     System.out.println("Enter the name associate to the agent");
     name = scanIn.nextLine();
     System.out.println("\n----------------------------------------------\n");
     nodes = applyItinerary(it.getItinerary());  
     Server_Interface si = (Server_Interface)Naming.lookup(nodes.pop());
     Agent agent = new Agent(name, nodes);
     agent.addVisitedNode(si.getName());
     System.out.println("\n----------------------------------------------\n");   
     System.out.println("Goodbye agent " + name + " server " + si.getName());
     System.out.println(si.receive(agent));
     System.out.println("Congratulations agent " +name);
    }
 
 private static Stack<String> applyItinerary(Stack<String> nodes)
    {   
     Scanner scanIn = new Scanner(System.in);   
     System.out.println("Enter the itinerary\nAvailable itineraries:\nSequential"
                     + ": \"seq\"\nRandom: \"alea\"\nOwn: \"own\"");
     
     itinerary_type = scanIn.nextLine();   
     
     Stack<String> tmp_stack = new Stack();
     List<String> tmp_list = new ArrayList();
     int iterations;
     
     switch(itinerary_type)
        {
         case "own":
             System.out.println("Sequential itirerary selected");
             return nodes;
             
         case "alea":
             Random randomizer = new Random();
             int random;
             iterations = nodes.size();
             System.out.println("Random itirerary selected");
             for(int index = 0; index < iterations; index++)
             tmp_list.add(nodes.pop());
             
             while(tmp_list.isEmpty() == false)
                { 
                 random = randomizer.nextInt(tmp_list.size());
                 tmp_stack.push(tmp_list.get(random));
                 tmp_list.remove(random);
                }
             return tmp_stack;
             
         case "seq":
             System.out.println("Own itirerary selected");
             iterations = nodes.size();
             for(int index = 0; index < iterations; index++)
             tmp_stack.push(nodes.pop());
             return tmp_stack;
             
         default:
             System.out.println("No such itinerary\nAvailable itineraries:\nSequential"
                     + ": \"seq\"\nRandom: \"alea\"\nOwn: \"own\"");
             return applyItinerary(nodes);
        }
    }
}
