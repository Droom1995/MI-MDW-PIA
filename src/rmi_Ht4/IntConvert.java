package rmi_Ht4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntConvert extends Remote{

    double convert(String from, String to, double amount) throws RemoteException;
}