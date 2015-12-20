package Main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Main_APP 
{
 public static void main(String[] args) throws RemoteException, InterruptedException
    {
     System.setProperty("java.rmi.server.hostname", "localhost");    
     Registry registry = LocateRegistry.createRegistry(5000); 
     Itinerary_Database it = new Itinerary_Database();
     registry.rebind("main", it);
     System.out.println("MAIN_APP working properly");
     while(!it.getSate())
        {
         TimeUnit.SECONDS.sleep(1);
        }   
     System.exit(0);
    }   
}
