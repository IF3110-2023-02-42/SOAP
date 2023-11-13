package models.implementation;
import lombok.Getter;
import lombok.Setter;
import models.BaseModel;

import javax.xml.bind.annotation.XmlEnum;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class DummyModel implements BaseModel {
    private Integer dummyInteger;
    private String dummyString;
    private DummyEnum dummyEnum;

    @XmlEnum(String.class)
    public enum DummyEnum {
        DUMMY1,
        DUMMY2,
        DUMMY3;

        public static DummyEnum fromStatusCode(String value) {
            for (DummyEnum status : DummyEnum.values()) {
                if (status.toString().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            return null;
        }
    }
    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.dummyInteger = rs.getInt("dummy_integer");
        this.dummyString = rs.getString("dummy_string");
        this.dummyEnum = DummyModel.DummyEnum.fromStatusCode(rs.getString("dummy_enum"));
    }
}
