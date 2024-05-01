package common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class PropertiesHelper {
    Properties applicationProperties;

    public PropertiesHelper() throws IOException {
        applicationProperties = new Properties();
        String applicationPath = getApplicationPath();
        Path path = Paths.get(applicationPath + "general.properties");
        try(InputStream inputStream = Files.newInputStream(path)){
            applicationProperties.load(inputStream);
        }
    }

    public String getApplicationPath() {
        String applicationPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        if (applicationPath.startsWith("/"))
            return applicationPath.substring(1);
        return applicationPath;
    }

    public boolean getBooleanProperty(String key) {
        String property = getStringProperty(key);
        return Boolean.parseBoolean(property);
    }

    public String getStringProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String property = getStringProperty(key);
        return Integer.parseInt(property);
    }
}

