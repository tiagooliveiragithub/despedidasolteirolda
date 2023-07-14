package com.tiago.despedidasolteirolda.data;

import com.tiago.despedidasolteirolda.entities.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager implements Serializable {

    private static FileManager fl = null;
    private Map<String, Admin> admins = new HashMap<>();
    private Map<String, Client> clients = new HashMap<>();
    private Map<String, Provider> providers = new HashMap<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, Manager> managers = new HashMap<>();

    public Map<String, Admin> getAdmins() {return admins;}
    public Map<String, Client> getClients() {return clients;}
    public Map<String, Provider> getProviders() {return providers;}
    public Map<String, Employee> getEmployees() {return employees;}
    public Map<String, Manager> getManagers() {return managers;}

    public static FileManager getFileManager(){

        if (fl == null)
            fl = new FileManager();
        return fl;
    }

    public void serialize(String filename){

        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename + "\n");
        } catch(IOException ex){
            System.out.println("ErrorSerialize: " + ex.getMessage());
        }
    }

    public static void deserialize(String filename){

        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fl = (FileManager) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException ex){
            System.out.println("ErrorDeserialize: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            System.out.println("Repository class not found. " + ex.getMessage());
        }
    }
}
