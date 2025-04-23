import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
    String defaultConfigFile =  "config.txt";
    private final static Map<String, String> parsedKeyValuePair = new HashMap<>();


    public ConfigParser(String configFile, String environment) {

        if (!environment.isEmpty()) {
            if (environment.equals("production")) {
                parseConfigFile(defaultConfigFile);
            } else {
                File fileObj = new File(configFile + "." + environment.trim());
                if (!fileObj.exists()) {
                    System.err.println("Error: Configuration file not found: " + fileObj.getAbsolutePath());
                    return;
                }
                parseConfigFile(fileObj.getAbsolutePath());
            }

        } else {
            parseConfigFile(configFile);
        }
    }

    public ConfigParser() {
        parseConfigFile(defaultConfigFile);
    }



    void parseConfigFile(String file) {

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            String section = "";
            while((line = fileReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line.substring(1, line.length() - 1);
                    continue;
                }

                if (line.contains("=")) {
                    String[] data = line.split("=");

                    if (data.length > 1) {
                        addToMap(data, section);
                    }
                }

            }
           fileReader.close();
       } catch (IOException e) {
            System.err.println("Error: Configuration file not found or unreadable.");
        }

        for (Map.Entry<String, String> entry : parsedKeyValuePair.entrySet()) {
            System.out.println(entry.getKey() +" = "+ entry.getValue());
        }
    }

    
    public void addToMap(String[] data, String section) {
        if (data.length != 2) {
            System.err.println("Warning: Skipping malformed line: " + String.join("=", data));
            return;
        }
        String key = section.isEmpty() ? data[0].trim() : section.trim()+"."+data[0].trim();
        if (ConfigParser.parsedKeyValuePair.containsKey(key)) return;
        ConfigParser.parsedKeyValuePair.put(key, data[1].trim());
    }


    public String get(String key) {
        return parsedKeyValuePair.get(key);
    }
}
