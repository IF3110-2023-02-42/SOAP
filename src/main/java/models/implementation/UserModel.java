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
public class UserModel implements BaseModel {
    private Integer ID_Pengguna;
    private String nama;
    private String email;
    private String verificationStatus;
    private String tanggalPengajuan;

    public void constructFromSQL(ResultSet rs) throws SQLException {
        this.ID_Pengguna = rs.getInt("ID_Pengguna");
        this.nama = rs.getString("nama");
        this.email = rs.getString("email");
        this.verificationStatus = rs.getString("verificationStatus");
        this.tanggalPengajuan = rs.getTimestamp("tanggalPengajuan").toString();
    }
}
