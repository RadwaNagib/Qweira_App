package resources;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//mport org.json.JSONObject as OrgJson;


import java.io.FileReader;
public class ConfigReader {

    private static final String CONFIG_FILE_PATH = "src\\test\\java\\resources\\config.json";
    private static JSONObject config;

    static {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(CONFIG_FILE_PATH);
            config = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading config.json");
        }
    }

    public static String getBrowser() {
        return (String) config.get("browser");
    }

    public static String getBaseUrl() {
        return (String) config.get("baseUrl");
    }
    public static String Email() {
        return (String) config.get("email");
    }
    public static String PassWord() {
        return (String) config.get("password");
    }
    public static long timeOut() { return (long) config.get("timeout");


    }
   // Integer timeout = someLongValue.intValue();


}
