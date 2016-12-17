package rmi_Ht4;

import java.rmi.Naming;


public class Ht4Client {

    public static void main(String[] args) throws Exception{
        IntConvert client = (IntConvert)Naming.lookup("//localhost/convert");
        System.out.println(client.convert("USD","EUR",1.05));
    }
}