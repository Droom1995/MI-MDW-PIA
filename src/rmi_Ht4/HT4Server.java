package rmi_Ht4;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;

public class HT4Server extends UnicastRemoteObject implements IntConvert {

    private static final long serialVersionUID = 1L;
    Hashtable<String, Double> numbers = new Hashtable<String, Double>();

    protected HT4Server() throws RemoteException {
        super();
        numbers.put("USDEUR", 0.84);
        numbers.put("GBPEUR", 0.87);
        numbers.put("GBPUSD", 0.64);
        numbers.put("EURGBP", 1.12);
        numbers.put("USDGBP", 1.32);
        numbers.put("EURUSD", 1.20);
    }

    @Override
    public
    double convert(String from, String to, double amount) throws RemoteException{
        return numbers.get(from+to)*amount;
    }


    public static void main(String[] args) {
        try {
            /*
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            */
            LocateRegistry.createRegistry(1099);

            HT4Server server = new HT4Server();
            Naming.rebind("//0.0.0.0/convert", server);

            System.out.println("Server started...");

        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }

    }


}