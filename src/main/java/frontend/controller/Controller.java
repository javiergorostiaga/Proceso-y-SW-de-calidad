package frontend.controller;

import java.util.*;
import java.io.*;
import frontend.swing.Principal;
import frontend.remote.RMIServiceLocator;

import common.properties.PropertyManager;
import common.properties.AppPropertyManager;

import java.rmi.RemoteException;

public class Controller
{
    private RMIServiceLocator serviceLocator = null;

    public Controller(String[] args) throws Exception
    {
        PropertyManager apm = new AppPropertyManager();
        Properties p = apm.getProperties();
        serviceLocator = new RMIServiceLocator();
        serviceLocator.setService(p.getProperty("ip"),p.getProperty("port"),p.getProperty("serviceName"));
        //new Principal(this);
        Principal.exportData(this);
    }

    public boolean exportPeliculas() throws Exception
    {
        return serviceLocator.getService().exportPeliculas();
    }

    public void exit()
    {
        System.exit(0);
    }

    public static void main(String[] args) throws Exception
    {
        new Controller(args);
    }
}
