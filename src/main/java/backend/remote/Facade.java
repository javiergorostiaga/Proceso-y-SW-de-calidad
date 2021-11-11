package backend.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.remote.IFacade;

public class Facade extends UnicastRemoteObject implements IFacade {
    public Facade() throws RemoteException {

    }

    public static void main(String[] args) throws Exception {

    }
}
