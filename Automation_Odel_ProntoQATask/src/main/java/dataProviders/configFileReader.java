package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configFileReader {
    public static String getPropertyValue(String propertyKey){
        String propertyFilepath = constantConfigs.propertyFilePath;
        Properties properties = new Properties();
        BufferedReader reader;

        try{
            reader= new BufferedReader(new FileReader(propertyFilepath));
            properties.load(reader);

            String value= properties.getProperty(propertyKey);
            if(value.isEmpty()){
                throw new RuntimeException("The given property not found");
            }else{
                return value;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Property file is not found" +e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
