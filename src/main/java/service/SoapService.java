package service;

import controller.DummyController;
import models.implementation.DummyModel;
import repository.implementation.DummyRepo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

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
}
