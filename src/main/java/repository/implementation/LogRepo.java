package repository.implementation;

import models.implementation.LogModel;
import repository.BaseRepo;
import utils.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogRepo extends BaseRepo<LogModel> {
    private static LogRepo instance;

    protected LogRepo(DatabaseHandler db, String tableName) {
        super(db, tableName);
    }

    public static LogRepo getInstance() {
        if (instance == null) {
            instance = new LogRepo(
                    DatabaseHandler.getInstance(),
                    "Logging");
        }
        return instance;
    }

    @Override
    public List<LogModel> findAll() throws SQLException {
        try {
            List<LogModel> result = new ArrayList<>();
            Statement stmt = this.db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
            while (rs.next()) {
                LogModel newLog = new LogModel();
                newLog.constructFromSQL(rs);
                result.add(newLog);
            }
            return result;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    @Override
    public LogModel create(LogModel log) throws SQLException {
        try {
            String query = "INSERT INTO " + this.tableName
                    + " (ID_Log, requestDescription, IP, endpoint, createdAt) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = this.db.prepareQuery(query, log.getID_Log(), log.getDescription(), log.getIP(),
                    log.getEndpoint(), log.getCreatedAt());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                return log;
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public LogModel getRecordById(String ID_Log) throws SQLException {
        try {
            String query = "SELECT * FROM " + this.tableName + " WHERE ID_Log = ? ";
            PreparedStatement pstmt = this.db.prepareQuery(query, ID_Log);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LogModel newLog = new LogModel();
                newLog.constructFromSQL(rs);
                return newLog;
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    public LogModel newRecord(String description, String IP, String endpoint) throws SQLException {
        try {
            String query = "INSERT INTO " + this.tableName + " (requestDescription, IP, endpoint) VALUES (?, ?, ?)";
            PreparedStatement pstmt = this.db.prepareQuery(query, description, IP, endpoint);
            int ar = pstmt.executeUpdate();
            if (ar > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    LogModel newLog = new LogModel();
                    newLog.constructFromSQL(rs);
                    return newLog;
                }
            }
            return null;
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }

    @Override
    public LogModel update(LogModel log) throws SQLException {
        try {
            Statement stmt = this.db.getConnection().createStatement();
            int rs = stmt.executeUpdate(
                    "UPDATE " + this.tableName +
                            " SET requestDescription = '" + log.getDescription() +
                            "', IP = '" + log.getIP() +
                            "', endpoint = '" + log.getEndpoint() +
                            "', createdAt = '" + log.getCreatedAt() +
                            "' WHERE ID_Log = " + log.getID_Log());

            if (rs > 0) {
                return log;
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }

    @Override
    public LogModel delete(LogModel log) throws SQLException {
        try {
            Statement stmt = this.db.getConnection().createStatement();
            int rs = stmt.executeUpdate("DELETE FROM " + this.tableName + " WHERE ID_Log = " + log.getID_Log());

            if (rs > 0) {
                return log;
            }
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
}
