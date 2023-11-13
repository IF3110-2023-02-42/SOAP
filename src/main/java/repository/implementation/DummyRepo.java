package repository.implementation;


import models.implementation.DummyModel;
import repository.BaseRepo;
import utils.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DummyRepo extends BaseRepo<DummyModel> {
    private static DummyRepo instance;

    protected DummyRepo(DatabaseHandler db, String tableName) {
        super(db, tableName);
    }

    public static DummyRepo getInstance() {
        if (instance == null) {
            instance = new DummyRepo(
                    DatabaseHandler.getInstance(),
                    "dummy_table");
        }
        return instance;
    }

    @Override
    public List<DummyModel> findAll() throws SQLException {
        List<DummyModel> result = new ArrayList<>();

        Statement stmt = this.db.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.tableName);
        while (rs.next()) {
            DummyModel newDummy = new DummyModel();
            newDummy.constructFromSQL(rs);
            result.add(newDummy);
        }
        return result;
    }

    @Override
    public DummyModel create(DummyModel dummy) throws SQLException {
        Statement stmt = this.db.getConnection().createStatement();
        int rs = stmt.executeUpdate("INSERT INTO " + this.tableName + " (dummy_integer, dummy_string) VALUES (" + dummy.getDummyInteger() + ", " + dummy.getDummyString() + ")");
        if (rs > 0) {
            return dummy;
        }
        return null;
    }

    @Override
    public DummyModel update(DummyModel dummy) throws SQLException {
        Statement stmt = this.db.getConnection().createStatement();
        int rs = stmt.executeUpdate("UPDATE " + this.tableName + " SET status = '"
                + dummy.getDummyEnum().toString() + "' WHERE dummy_integer = "
                + dummy.getDummyInteger() + " AND dummy_string = "
                + dummy.getDummyString());
        if (rs > 0) {
            return dummy;
        }
        return null;
    }

    @Override
    public DummyModel delete(DummyModel dummy) throws SQLException {
        Statement stmt = this.db.getConnection().createStatement();
        int rs = stmt.executeUpdate("DELETE FROM " + this.tableName + " WHERE dummy_integer = " + dummy.getDummyInteger() + " AND dummy_string = " + dummy.getDummyString());
        if (rs > 0) {
            return dummy;
        }
        return null;
    }
}
