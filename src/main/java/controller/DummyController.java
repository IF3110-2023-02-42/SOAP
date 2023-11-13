package controller;

import models.implementation.DummyModel;
import repository.implementation.DummyRepo;

import java.util.List;

public class DummyController {
    public List<DummyModel> getAllDummyData() {
        try {
            return DummyRepo.getInstance().findAll();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
//    public boolean insertDummyData(DummyModel dummy) {
//        try {
//            return DummyRepo.getInstance().findAll();
//        } catch (Exception e) {
//            System.out.println("exception: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
}
