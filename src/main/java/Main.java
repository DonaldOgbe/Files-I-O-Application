
public class Main {

    public static void main(String[] args) {
        String environment = args.length > 0 ? args[0] : "";

        if (!environment.matches("production|staging|development|")) {
            System.err.println("Error: Invalid environment. Use 'production', 'staging', or 'development'.");
            return;
        }

        String configFile =  "config.txt";


        ConfigParser configParser;
        configParser =  environment.isEmpty() ? new ConfigParser() :
        new ConfigParser(configFile, environment);
    }

}
