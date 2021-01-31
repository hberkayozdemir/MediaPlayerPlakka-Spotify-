package Controller.Management;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;
import Controller.DataBase.*;
import javafx.fxml.FXML;
import java.sql.*;
import Model.BuilderPattern.*;
import Controller.PrototypePattern.*;
import Model.chain.*;
public class LoginProccess {


    public TextField RegisterScreenNS;
    public TextField RegisterScreenPwd;
    public TextField RegisterScreenCountry;
    public TextField RegisterScreenPwdAgain;
    public TextField RegisterScreenId;
    public TextField RegisterScreenPhone;
    @FXML
    public TextField LoginScreenID;
    @FXML
    public TextField LoginScreenPwd;
    Stage temp;
    private final static Gui path=new Gui();
    private AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
    private AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
    private AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }


    public String uzer;
    public String passworld;






    public void LoginScreenLoginBtn(InputEvent e) throws SQLException, IOException {
        AbstractLogger loggerChain = getChainOfLoggers();
        boolean temp = true;

        uzer = LoginScreenID.getText().toString();
        passworld=LoginScreenPwd.getText().toString();

        SQLConnection myconn = SQLConnection.getInstance();
        loggerChain.logMessage(AbstractLogger.DEBUG, "Sql connection is succed");
        Statement stmt=myconn.con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from users ");
        while(rs.next()) {//3

            if(uzer.equals(rs.getString(1)) && passworld.equals(rs.getString(3))) {
                temp = !temp;
                loggerChain.logMessage(AbstractLogger.INFO, "kullanıcı girişi başarılı");
            }
        }

        if(!temp) {


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"Client.fxml"));
            Stage stage = new Stage();
            Scene root = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("Plakka");
            stage.setScene(root);
            stage.show();
            final Node source = (Node) e.getSource();
            final Stage seagreen = (Stage) source.getScene().getWindow();
            seagreen.close();
        }else{

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"LoginFailScreen.fxml"));
                Stage stage = new Stage();
                Scene root = new Scene(fxmlLoader.load(), 700, 500);
                stage.setTitle("Plakka-Register-");
                stage.setScene(root);
                stage.show();
                final Node source = (Node) e.getSource();
                final Stage seagreen = (Stage) source.getScene().getWindow();
                seagreen.close();


            } catch (IOException Ex) {
                Ex.printStackTrace();
                //logge.logMessage(AbstractLogger.ERROR,);
                loggerChain.logMessage(AbstractLogger.ERROR, "151 at Controller.Management.LoginProcess.java");

            }
            loggerChain.logMessage(AbstractLogger.INFO, "kullanıcı girişi başarısız");}


    }

    public void LoginScreenRegisterBtn(InputEvent e){
// burda bir back end islemi yok


        AbstractLogger loggerChain = getChainOfLoggers();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"Register.fxml"));
            Stage stage = new Stage();
            Scene root = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("Plakka-Register-");
            stage.setScene(root);
            stage.show();
            final Node source = (Node) e.getSource();
            final Stage seagreen = (Stage) source.getScene().getWindow();
            seagreen.close();


        } catch (IOException Ex) {
            Ex.printStackTrace();
            //logge.logMessage(AbstractLogger.ERROR,);
            loggerChain.logMessage(AbstractLogger.ERROR, "152 at Controller.Management.LoginProcess.java");

        }
    }






    public void LoginFailBackButton(InputEvent e){
        AbstractLogger loggerChain = getChainOfLoggers();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"LoginScreen.fxml"));
            Stage stage = new Stage();
            Scene root = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("Plakka-Register-");
            stage.setScene(root);
            stage.show();
            final Node source = (Node) e.getSource();
            final Stage seagreen = (Stage) source.getScene().getWindow();
            seagreen.close();

        } catch (IOException Ex) {
            Ex.printStackTrace();
            //logge.logMessage(AbstractLogger.ERROR,);
            loggerChain.logMessage(AbstractLogger.ERROR, "152 at Controller.Management.LoginProcess.java");

        }
    }




    public void RegisterScreenBackBtn(InputEvent e){
// burda bir back end islemi yok

        AbstractLogger loggerChain = getChainOfLoggers();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"LoginScreen.fxml"));
            Stage stage = new Stage();
            Scene root = new Scene(fxmlLoader.load(), 700, 500);
            stage.setTitle("Plakka-Register-");
            stage.setScene(root);
            stage.show();
            final Node source = (Node) e.getSource();
            final Stage seagreen = (Stage) source.getScene().getWindow();
            seagreen.close();


        } catch (IOException Ex) {
            Ex.printStackTrace();
            loggerChain.logMessage(AbstractLogger.ERROR, "152 at Controller.Management.LoginProcess.java");
        }
    }

    public void RegisterScreenRegisterBtn(InputEvent e) throws Exception{


        String ID = RegisterScreenId.getText().toString();
        String ns = RegisterScreenNS.getText().toString();
        String Country = RegisterScreenCountry.getText().toString();
        String Phone = RegisterScreenPhone.getText().toString();
        String pwd = RegisterScreenPwd.getText().toString();
        String pwdAgain = RegisterScreenPwdAgain.getText().toString();

        if(pwd.equals(pwdAgain)) {

            User user = new User.Builder().id(ID).country(Country).name_and_surname(ns).password(pwd).phone(Phone).build();

            SQLConnection myconn = SQLConnection.getInstance();
            Statement stmt=myconn.con.createStatement();
            ResultSet rs=stmt.executeQuery("select ID from users");

            boolean exists=false;

            while(rs.next()) {
                if(ID.equals(rs.getString(1))) {
                    exists = true;
                    System.out.println("Ba�ka nick gir");

                    AbstractLogger loggerChain = getChainOfLoggers();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"RegisterFailScreen.fxml"));
                        Stage stage = new Stage();
                        Scene root = new Scene(fxmlLoader.load(),
                                700,
                                500);
                        stage.setTitle("Plakka-Register-");
                        stage.setScene(root);
                        stage.show();
                        final Node source = (Node) e.getSource();
                        final Stage seagreen = (Stage) source.getScene().getWindow();
                        seagreen.close();


                    } catch (IOException Ex) {
                        Ex.printStackTrace();
                        loggerChain.logMessage(AbstractLogger.ERROR, "152 at Controller.Management.LoginProcess.java");
                    }
                }

            }
            if(!exists) {
                String query = " insert into users(ID,name_and_surname,pass,phone,country) values (?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = myconn.con.prepareStatement(query);
                preparedStmt.setString (1,user.getId());
                preparedStmt.setString (2,user.getName_and_surname());
                preparedStmt.setString (3,user.getPassword());
                preparedStmt.setString (4,user.getPhone());
                preparedStmt.setString   (5,user.getCountry());

                preparedStmt.execute();
            }
        }

        else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"RegisterFailScreen.fxml"));
                Stage stage = new Stage();
                Scene root = new Scene(fxmlLoader.load(),
                        700,
                        500);
                stage.setTitle("Plakka-Register-");
                stage.setScene(root);
                stage.show();
                final Node source = (Node) e.getSource();
                final Stage seagreen = (Stage) source.getScene().getWindow();
                seagreen.close();


            } catch (IOException Ex) {
                Ex.printStackTrace();

            }
        }
    }





}

