package common.properties;

import lombok.Getter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager
{
    private String fileName;
    private FileReader reader;
    private FileWriter writer;
    @Getter private Properties properties;

    public PropertyManager(String fileName)
    {
        this.fileName = fileName;
        try
        {
            reader = new FileReader(fileName);
            properties = new Properties();
            properties.load(reader);

        } catch (IOException ex) {
            System.err.println("* Exception selecting properties: " + ex.getMessage());
        }
    }

    public void setProperty(String key, String value)
    {
        try
        {
            reader.close();
            writer = new FileWriter(fileName);

            properties.setProperty(key, value);
            properties.store(writer, null);

            writer.close();

            reader = new FileReader(fileName);
            properties = new Properties();
            properties.load(reader);
        } catch (IOException ex) {
            System.err.println("* Exception updating properties: " + ex.getMessage());
        }
    }
}
