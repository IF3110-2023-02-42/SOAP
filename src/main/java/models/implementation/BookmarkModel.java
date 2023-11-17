package models.implementation;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@XmlRootElement
public class BookmarkModel implements BaseModel{
    private Integer ID_Bookmark;
    private Integer ID_Material;
    private Integer ID_Pengguna;


    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.ID_Bookmark = rs.getInt("ID_Bookmark");
        this.ID_Material = rs.getInt("ID_Material");
        this.ID_Pengguna = rs.getInt("ID_Pengguna");
    }
}
