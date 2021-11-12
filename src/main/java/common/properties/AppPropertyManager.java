package common.properties;

public class AppPropertyManager extends PropertyManager
{
    private static String fileName = "resources/app.properties";

    public AppPropertyManager()
    {
        super(fileName);
    }
}
