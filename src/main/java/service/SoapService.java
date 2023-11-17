package service;

import controller.DummyController;
import controller.LogController;
import controller.UserController;
import controller.BookmarkController;
import models.implementation.DummyModel;
import models.implementation.LogModel;
import models.implementation.UserModel;
import models.implementation.BookmarkModel;
import utils.EnviromentHandler;
import utils.EmailHandler;

import javax.jws.WebMethod;
import javax.jws.WebService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;

@WebService
public class SoapService {
    private DummyController dummyController = new DummyController();
    private LogController logController = new LogController();
    private UserController userController = new UserController();

    @WebMethod
    public String TestConnection(String name) {
        return "Hello " + name;
    }

    public void SendEmail(String destinationEmail, String subject, String message) {
        String smtpHostServer = "sandbox.smtp.mailtrap.io";
        String emailID = "a4b843fe33aa0a";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer); // SMTP Host
        props.put("mail.smtp.port", "2525"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

        String password = "5316ec9ed107ba";

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailID, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailHandler.sendEmail(session, destinationEmail, subject, message);
    }

    @WebMethod
    public List<DummyModel> GetDummyData() {
        try {
            return this.dummyController.getAllDummyData();
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<String> getApiKey() {
        EnviromentHandler env = EnviromentHandler.getInstance();
        String RestApiKey = env.get("STUDYDOJO_REST_API_KEYS_TO_SOAP");
        String PhpApiKey = env.get("STUDYDOJO_PHP_API_KEYS_TO_SOAP");
        List<String> response = new ArrayList<>();
        response.add("Rest");
        response.add(RestApiKey);
        response.add("Php");
        response.add(PhpApiKey);
        return response;
    }

    @WebMethod
    public LogModel newRecord() throws SQLException {
        try {
            String description = "Description is description";
            String IP = "192.168.1.1";
            String endpoint = "/api/ipa";
            return this.logController.newRecord(description, IP, endpoint);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<LogModel> getAllLog() throws SQLException {
        try {
            return this.logController.getAllLog();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<UserModel> getAllRequest() throws SQLException {
        try {

            return this.userController.getAllUser();
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public UserModel updateStatus(int ID_Pengguna, String verificationStatus) {
        try {
            UserModel user = this.userController.updateStatus(ID_Pengguna, verificationStatus);
            String subject = "Verification Status";
            String message;
            if (verificationStatus == "rejected") {
                message = "Halo " + user.getNama()
                        + ", Mohon maaf pengajuan akun premium Anda ditolak. Silakan coba lagi di lain waktu !";
            } else if (verificationStatus == "accepted") {
                message = "Halo " + user.getNama() + ", Selamat pengajuan akun premium Anda diterima!";
            } else {
                message = "Halo " + user.getNama() + ", Pengajuan anda sedang diproses";
            }
            this.SendEmail(user.getEmail(), subject, message);
            return user;
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public UserModel getUserStatus(int ID_Pengguna) {
        try {
            return this.userController.getUser(ID_Pengguna);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<BookmarkModel> findBookmarkByID(int ID_Pengguna) {
        try {
            BookmarkController controller = new BookmarkController();
            return controller.findByID(ID_Pengguna);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public void addBookmark(int ID_Pengguna, int ID_Material) {
        try {
            BookmarkController controller = new BookmarkController();
            controller.addBookmark(ID_Pengguna, ID_Material);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
        }
    }

    @WebMethod
    public String addUserRequest(int ID_Pengguna, String nama, String email) {
        try {
            return this.userController.createRequest(ID_Pengguna, nama, email);
        } catch (Exception er) {
            System.out.println(er.getMessage());
            er.printStackTrace();
            return "Something wrong";
        }
    }

}
