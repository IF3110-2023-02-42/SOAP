package controller;

import models.implementation.UserModel;
import repository.implementation.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserRepo repo = UserRepo.getInstance();

    public List<UserModel> getAllUser() {
        try {
            return this.repo.findAll();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserModel newRecord(int ID_Pengguna, String nama, String email, String verificationStatus)
            throws SQLException {
        try {
            return this.repo.newRecord(ID_Pengguna, nama, email, verificationStatus);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserModel updateStatus(int ID_Pengguna, String verificationStatus) {
        try {
            return this.repo.update(ID_Pengguna, verificationStatus);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserModel getUser(int ID_Pengguna) {
        try {
            return this.repo.getRecordById(ID_Pengguna);
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
