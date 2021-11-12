package common.remote;

import java.rmi.Remote;
import java.util.Date;

public interface IFacade extends Remote
{
    boolean exportPeliculas() throws Exception;
}
