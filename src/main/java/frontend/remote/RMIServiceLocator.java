package frontend.remote;

import common.remote.IFacade;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServiceLocator
{
    private IFacade service = null;

    public RMIServiceLocator() { }

    public void setService(String ip, String port, String serviceName)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(((Integer.valueOf(port))));
            String name = "//" + ip + ":" + port + "/" + serviceName;
            service = (IFacade) registry.lookup(name);
            System.out.println("* Connection established with server");

        }
        catch (Exception e)
        {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public IFacade getService()
    {
        return service;
    }
}
