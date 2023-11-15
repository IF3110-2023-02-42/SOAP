package repository.implementation;

import models.implementation.UserModel;
import repository.BaseRepo;
import utils.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo extends BaseRepo<UserModel> {
    private static UserRepo instance;

    protected UserRepo(DatabaseHandler db, String tableName) {
        super(db, tableName);
    }

    public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo(
                    DatabaseHandler.getInstance(),
                    "User");
        }
        return instance;
    }

    @Override
    public List<UserModel> findAll() throws SQLException {
        try {
            List<UserModel> result = new ArrayList<>();
            Statement stmt = this.db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
            while (rs.next()) {
                UserModel newUser = new UserModel();
                newUser.constructFromSQL(rs);
                result.add(newUser);
            }
            return result;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    @Override
    public UserModel create(UserModel user) throws SQLException {
        try {
            String query = "INSERT INTO " + this.tableName
                    + " (ID_Pengguna, nama, email, verificationStatus) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = this.db.prepareQuery(query, user.getID_Pengguna(), user.getNama(),
                    user.getEmail(), user.getVerificationStatus());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                return user;
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public UserModel getRecordById(int ID_Pengguna) throws SQLException {
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE ID_Pengguna = ? ";
            PreparedStatement pstmt = this.db.prepareQuery(query, ID_Pengguna);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UserModel newUser = new UserModel();
                newUser.constructFromSQL(rs);
                return newUser;
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public UserModel newRecord(int ID_Pengguna, String nama, String email, String verificationStatus)
            throws SQLException {
        try {
            String query = "INSERT INTO " + this.tableName
                    + " (ID_Pengguna, nama, email, verificationStatus) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = this.db.prepareQuery(query, ID_Pengguna, nama, email, verificationStatus);
            int ar = pstmt.executeUpdate();
            if (ar > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    UserModel newUser = new UserModel();
                    newUser.constructFromSQL(rs);
                    return newUser;
                }
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    @Override
    public UserModel update(UserModel user) throws SQLException {
        try {
            String query = "UPDATE " + this.tableName
                    + " SET nama  =? email =? verificationStatus =? tanggalPengajuan=? WHERE ID_Pengguna=?";
            PreparedStatement pstmt = this.db.prepareQuery(query, user.getNama(), user.getEmail(),
                    user.getVerificationStatus(), user.getTanggalPengajuan());
            int rs = pstmt.executeUpdate();

            if (rs > 0) {
                return user;
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }

    public UserModel update(int ID_Pengguna, String verificationStatus) throws SQLException {
        try {
            String query = "UPDATE " + this.tableName
                    + " SET verificationStatus = ? WHERE ID_Pengguna = ?";
            PreparedStatement pstmt = this.db.prepareQuery(query, verificationStatus, ID_Pengguna);
            int rs = pstmt.executeUpdate();

            if (rs > 0) {
                return this.getRecordById(ID_Pengguna);
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }

    @Override
    public UserModel delete(UserModel user) throws SQLException {
        try {
            String query = "DELETE FROM " + this.tableName + " WHERE ID_Pengguna = ?";
            PreparedStatement pstmt = this.db.prepareQuery(query, user.getID_Pengguna());
            int rs = pstmt.executeUpdate();

            if (rs > 0) {
                return user;
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
}
