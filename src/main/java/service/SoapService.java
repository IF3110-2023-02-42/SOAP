package service;

import controller.DummyController;
import controller.LogController;
import controller.UserController;
import models.implementation.DummyModel;
import models.implementation.LogModel;
import models.implementation.UserModel;
import utils.EnviromentHandler;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService
public class SoapService {
    private DummyController dummyController = new DummyController();
    private LogController logController = new LogController();
    private UserController userController = new UserController();

    @WebMethod
    public String TestConnection(String name) {
        return "Hello " + name;
    }

    @WebMethod
    public List<DummyModel> GetDummyData() {
        try {
            return this.dummyController.getAllDummyData();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<String> getApiKey() {
        EnviromentHandler env = EnviromentHandler.getInstance();
        String RestApiKey = env.get("STUDYDOJO_REST_API_KEYS_TO_SOAP");
        String PhpApiKey = env.get("STUDYDOJO_PHP_API_KEYS_TO_SOAP");
        List<String> response = new ArrayList<>();
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
            return this.logController.newRecord(description, IP, endpoint);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<LogModel> getAllLog() throws SQLException {
        try {
            return this.logController.getAllLog();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<UserModel> getAllRequest() throws SQLException {
        try {
            return this.userController.getAllUser();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public UserModel updateStatus(int ID_Pengguna, String verificationStatus) {
        try {
            return this.userController.updateStatus(ID_Pengguna, verificationStatus);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public UserModel getUserStatus(int ID_Pengguna) {
        try {
            return this.userController.getUser(ID_Pengguna);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

}
