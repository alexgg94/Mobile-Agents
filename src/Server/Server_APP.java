package Server;

import Main.Itinerary_Database_Interface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server_APP 
{ 
 public static void main(String args[]) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException
    {
     Itinerary_Database_Interface mt = (Itinerary_Database_Interface)Naming.lookup("rmi://localhost:5000/main");
     String ip = "localhost";
     String name;
     int RMI_port;
     System.setProperty("java.rmi.server.hostname", ip);
     String input;
     Scanner scanIn = new Scanner(System.in);
     
     while(true)
        {
          System.out.println("What would you like to do?");
          System.out.println("1 - Add new node");
          System.out.println("2 - See all nodes");
          System.out.println("3 - Exit");

          input = scanIn.nextLine();
          
          switch(input)
            {
              case "1":
                   System.out.println("Enter the name associate to your object");
                   name = scanIn.nextLine();
     
                   System.out.println("Enter the RMI port");
                   while(true)
                    {
                     try
                        {
                         RMI_port = Integer.parseInt(scanIn.nextLine());
                         break;
                        }
                     catch(Exception Ex)
                        {
                         System.out.println("Port format not correct. Try again please");
                        }
                     }
                    
                    try
                        {
                         Registry registry = LocateRegistry.createRegistry(RMI_port); 
                         Server server = new Server();
                         server.setName(name);
                         registry.rebind(name, server);
                         System.out.println("The remote object has been registered successfully");
                         System.out.println("\n----------------------------------------------\n");
                         mt.addNode("rmi://"+ip+":"+RMI_port+"/"+name); 
                        }
                    
                    catch(Exception ex)
                        {
                         System.out.println("The remote object could not be registered because of:\t" + ex);
                         System.out.println("\n----------------------------------------------\n");
                        }                   
                    break; 
                  
              case "2":
                  System.out.println(mt.getItinerary());
                  System.out.println("\n----------------------------------------------\n");
                  break;
                  
              case "3":
                  mt.close();
                  System.exit(0);
                  
              default:
                  System.out.println("Option not available");
                  break;
            }
        }
    }     
}
