package models;

public interface BaseModel {
    public void constructFromSQL(java.sql.ResultSet rs) throws java.sql.SQLException;
}