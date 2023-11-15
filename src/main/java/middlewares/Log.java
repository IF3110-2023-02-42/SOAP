package middlewares;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.ws.developer.JAXWSProperties;
import controller.LogController;
import models.implementation.LogModel;
import org.w3c.dom.NodeList;
import repository.implementation.LogRepo;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

public class Log implements SOAPHandler<SOAPMessageContext> {
    private void record(SOAPMessageContext smc) throws SOAPException, SQLException {
        try {
            boolean isResponse = (boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            HttpExchange httpExchange = (HttpExchange) smc.get(JAXWSProperties.HTTP_EXCHANGE);

            if(!isResponse) {
                SOAPPart soapPart = smc.getMessage().getSOAPPart();
                SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                SOAPBody soapBody = soapEnvelope.getBody();
                System.out.println(soapBody);

                Node operation = (Node) soapBody.getChildNodes().item(1);
                String description = String.format("%s", operation.getLocalName());

                NodeList parameters = operation.getChildNodes();
                for (int i = 1; i < parameters.getLength(); i += 2) {
                    description = String.format("%s %s(%s)", description, parameters.item(i).getLocalName(), parameters.item(i).getTextContent());
                }

                LogController controller = new LogController();

                String ip_address = httpExchange.getRemoteAddress().getAddress().getHostAddress();
                Number port = httpExchange.getRemoteAddress().getPort();
                String endpoint = httpExchange.getRequestURI().getPath();
                controller.newRecord(description, ip_address+":"+port.toString(), endpoint);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    private String buildDesc(String client, Object ...params) {
        String paramsStr = Arrays.stream(params)
                .map(e -> "[" + e + "]")
                .reduce((a, b) -> a + "," + b)
                .orElse("");
        return client + ":parameters{" + paramsStr + "}";
    }

    public Set<QName> getHeaders() {
        return null;
    }
    public boolean handleMessage(SOAPMessageContext smc) {
        try {
            record(smc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean handleFault(SOAPMessageContext smc) {
        try {
            record(smc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void close(MessageContext messageContext) {
        // DO NOTHING
    }
}
