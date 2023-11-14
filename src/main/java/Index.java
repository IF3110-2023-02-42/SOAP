import service.SoapService;
import utils.ConfigHandler;

import javax.xml.ws.Endpoint;

public class Index {
    private final static String SERVER_HOST_KEY = "server.host";
    private final static String SERVER_PORT_KEY = "server.port";
    private final static String SERVER_ENTRY_KEY = "server.entry";
    public static void main(String[] args) {
        try {
            ConfigHandler ch = ConfigHandler.getInstance();
            String host = ch.get(SERVER_HOST_KEY);
            String port = ch.get(SERVER_PORT_KEY);
            String entry = ch.get(SERVER_ENTRY_KEY);

            System.out.println("Trying to connect to "+host+":"+port);
            Endpoint.publish(host+":"+port+entry, new SoapService());
            System.out.println("Server started at "+host+":"+port+entry);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
