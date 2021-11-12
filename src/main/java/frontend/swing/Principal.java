package frontend.swing;

import frontend.controller.Controller;

public class Principal
{
    public static boolean exportData(final Controller controller) throws Exception
    {
        return controller.exportPeliculas();
    }
}
