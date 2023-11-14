package service;

import controller.DummyController;
import controller.LogController;
import models.implementation.DummyModel;
import models.implementation.LogModel;
import repository.implementation.DummyRepo;
import repository.implementation.LogRepo;
import utils.EnviromentHandler;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
public class SoapService {
    @WebMethod
    public String TestConnection(String name){
        return "Hello " + name;
    }

    @WebMethod
    public List<DummyModel> GetDummyData(){
        try {
            DummyController controller = new DummyController();
            return controller.getAllDummyData();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @WebMethod
    public List<String> getApiKey(){
        EnviromentHandler env = EnviromentHandler.getInstance();
        String RestApiKey = env.get("STUDYDOJO_REST_API_KEYS_TO_SOAP");
        String PhpApiKey = env.get("STUDYDOJO_PHP_API_KEYS_TO_SOAP");
        List<String> response= new ArrayList<>();
        response.add("Rest");
        response.add(RestApiKey);
        response.add("Php");
        response.add(PhpApiKey);
        return response;
    }

    @WebMethod
    public LogModel newRecord() throws SQLException {
        try {
            String description = "Description is description";
            String IP = "192.168.1.1";
            String endpoint = "/api/ipa";
            LogController controller = new LogController();
            return controller.newRecord(description,IP,endpoint);
        } catch (Exception er){
            er.printStackTrace();
            return null;
        }
    }
    @WebMethod
    public List<LogModel> getAllLog() throws SQLException {
        try {
            LogController controller = new LogController();
            return controller.getAllLog();
        } catch (Exception er){
            er.printStackTrace();
            return null;
        }
    }
}
