
# Files I/O Application - Config File Parser (Week 2 Project - Decagon Curriculum)

This project demonstrates how to handle file input and output in Java by parsing environment-based configuration files. It simulates a real-world scenario where applications behave differently based on the environment (e.g., development, staging, production).

The application reads .txt config files at startup, processes the values into a Map, and allows retrieval of data using dot-notation keys like application.name.

## Features

- Parses custom .txt configuration files
- Supports nested sections (e.g. [application]) using dot-notation
- Environment-aware (e.g. loads config.production.txt or config.development.txt)
- Returns the first matching value for repeated keys
- Graceful handling of missing file names with a default fallback


## Usage/Examples


```java
public class Main {
    public static void main(String[] args) {

        // Using the default config file ("config.txt")
        ConfigParser defaultConfig = new ConfigParser();
        String dbName = defaultConfig.get("dbname");

        // Using a specific environment (e.g., reads "config.txt.development")
        ConfigParser envConfig = new ConfigParser("config.txt", "development");
        String appName = envConfig.get("application.name");

        System.out.println("Database: " + dbName);
        System.out.println("App Name: " + appName);
        String appName = config.get("application.name");
    }
}   
```



