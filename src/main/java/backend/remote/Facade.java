package backend.remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Properties;

import backend.app_service.AppService;
import backend.objects.Pelicula;
import common.remote.IFacade;
import common.properties.PropertyManager;
import common.properties.AppPropertyManager;
import backend.dao.DAO;

public class Facade extends UnicastRemoteObject implements IFacade {
    private AppService app_service;
    private static Registry registry;
    private DAO dao;

    public Facade() throws RemoteException
    {
        this.app_service = new AppService();
        this.dao = new DAO();
    }

    @Override
    public boolean exportPeliculas() throws Exception {
        System.out.println("* Received exportData() call from Client");
        if (app_service.exportPeliculas()) //luego meteremos and si hay que hacer mas exports
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) throws Exception
    {
        PropertyManager apm = new AppPropertyManager();
        Properties p = apm.getProperties();

        String name = "//" + p.getProperty("ip") + ":" + p.getProperty("port") + "/" + p.getProperty("serviceName");
        System.setProperty("java.rmi.server.CodeBase", name);
        System.setProperty("java.security.policy", "resources/java.policy");

        try {
            IFacade server = new Facade();
            registry = LocateRegistry.createRegistry((Integer.valueOf(p.getProperty("port"))));
            registry.rebind(name, server);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public ArrayList<Pelicula> selectPeliculas() throws Exception {
        System.out.println("* Received mostrarData() call from Client");
        return dao.selectPeliculas();


    }
}
