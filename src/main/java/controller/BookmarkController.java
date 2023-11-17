package controller;

import models.implementation.BookmarkModel;
import repository.implementation.BookmarkRepo;

import java.sql.SQLException;
import java.util.List;

public class BookmarkController {
    public List<BookmarkModel> findByID(Integer ID_Pengguna) throws SQLException{
        try {
            return BookmarkRepo.getInstance().findBookmarkByID(ID_Pengguna);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public void addBookmark(Integer ID_Pengguna, Integer ID_Material) throws SQLException{
        try {
            BookmarkRepo.getInstance().addBookmark(ID_Pengguna, ID_Material);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
