package repository.implementation;

import models.implementation.BookmarkModel;
import models.implementation.LogModel;
import repository.BaseRepo;
import utils.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepo extends BaseRepo<BookmarkModel>{
    private static BookmarkRepo instance;

    protected BookmarkRepo(DatabaseHandler db, String tableName){
        super(db, tableName);
    }

    public static BookmarkRepo getInstance(){
        if (instance == null){
            instance = new BookmarkRepo(DatabaseHandler.getInstance(), "Bookmark");
        }
        return instance;
    }

    public List<BookmarkModel> findBookmarkByID(Integer ID_Pengguna) throws SQLException{
        try{
            List<BookmarkModel> result = new ArrayList<>();
            String query = "SELECT * FROM " +  this.tableName + " WHERE ID_Pengguna = ? ";
            PreparedStatement pstmt = this.db.prepareQuery(query, ID_Pengguna);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                BookmarkModel newBookmark = new BookmarkModel();
                newBookmark.constructFromSQL(rs);
                result.add(newBookmark);
            }
            return result;
        } catch (Exception err){
            err.printStackTrace();
            throw err;
        }
    }

    public void addBookmark(Integer ID_Pengguna, Integer ID_Material) throws SQLException{
        try{
            String query = "INSERT INTO " + this.tableName + " VALUES (, ?, ?)";
            PreparedStatement pstmt = this.db.prepareQuery(query, ID_Material, ID_Pengguna);
            pstmt.executeQuery();
        } catch (Exception err) {
            err.printStackTrace();
            throw err;
        }
    }
}
