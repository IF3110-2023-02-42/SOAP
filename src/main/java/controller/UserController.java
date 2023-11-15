package controller;

import models.implementation.UserModel;
import repository.implementation.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    public List<UserModel> getAllUser() {
        try {
            return UserRepo.getInstance().findAll();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserModel newRecord(int ID_Pengguna, String nama, String email, String verificationStatus)
            throws SQLException {
        try {
            return UserRepo.getInstance().newRecord(ID_Pengguna, nama, email, verificationStatus);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserModel updateStatus(int ID_Pengguna, String verificationStatus) {
        try {
            return UserRepo.getInstance().update(ID_Pengguna, verificationStatus);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
