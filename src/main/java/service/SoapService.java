package service;

import controller.DummyController;
import controller.LogController;
import controller.UserController;
import controller.BookmarkController;
import models.implementation.DummyModel;
import models.implementation.LogModel;
import models.implementation.UserModel;
import models.implementation.BookmarkModel;
import utils.EnviromentHandler;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService
public class SoapService {
    @WebMethod
    public String TestConnection(String name) {
        return "Hello " + name;
    }

    @WebMethod
    public List<DummyModel> GetDummyData() {
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
            LogController controller = new LogController();
            return controller.newRecord(description, IP, endpoint);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<LogModel> getAllLog() throws SQLException {
        try {
            LogController controller = new LogController();
            return controller.getAllLog();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<UserModel> getAllRequest() throws SQLException {
        try {
            UserController controller = new UserController();
            return controller.getAllUser();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public UserModel updateStatus(int ID_Pengguna, String verificationStatus) {
        try {
            UserController controller = new UserController();
            return controller.updateStatus(ID_Pengguna, verificationStatus);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<BookmarkModel> findBookmarkByID(int ID_Pengguna){
        try{
            BookmarkController controller = new BookmarkController();
            return controller.findByID(ID_Pengguna);
        } catch (Exception er){
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void addBookmark(int ID_Pengguna, int ID_Material){
        try{
            BookmarkController controller = new BookmarkController();
            controller.addBookmark(ID_Pengguna, ID_Material);
        } catch (Exception er){
            System.out.println(er.getMessage());
            er.printStackTrace();
        }
    }
}
