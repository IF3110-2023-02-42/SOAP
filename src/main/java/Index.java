import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import middlewares.Cors;
import middlewares.Log;
import service.SoapService;
import utils.ConfigHandler;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

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

            HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(port)), 0);
            HttpContext context = server.createContext(entry);

            Endpoint endpoint = Endpoint.create(new SoapService());
            List<Handler> handlerChain = new ArrayList<>();
            handlerChain.add(new Log());
            endpoint.getBinding().setHandlerChain(handlerChain);
            endpoint.publish(context);

            context.getFilters().add(new Cors());
            server.start();
            System.out.println("Server started at "+host+":"+port+entry);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
