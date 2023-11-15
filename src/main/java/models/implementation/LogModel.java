package models.implementation;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Getter
@Setter
@XmlRootElement
public class LogModel implements BaseModel {
    private Integer ID_Log;
    private String description;
    private String IP;
    private String endpoint;
    private String createdAt;

    public void constructFromSQL(ResultSet rs) throws SQLException{
        this.ID_Log = rs.getInt("ID_Log");
        this.description = rs.getString("description");
        this.IP = rs.getString("IP");
        this.endpoint = rs.getString("endpoint");
        this.createdAt = rs.getTimestamp("createdAt").toString();
    }
}
