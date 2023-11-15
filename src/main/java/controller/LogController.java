package controller;

import models.implementation.LogModel;
import repository.implementation.LogRepo;

import java.sql.SQLException;
import java.util.List;

public class LogController {
    public List<LogModel> getAllLog() {
        try {
            return LogRepo.getInstance().findAll();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public LogModel newRecord(String description, String IP, String endpoint) throws SQLException {
        try {
            return LogRepo.getInstance().newRecord(description, IP, endpoint);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
