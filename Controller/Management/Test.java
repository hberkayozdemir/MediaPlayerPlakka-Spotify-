package Controller.Management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUI/LoginScreen.fxml"));
        primaryStage.setTitle("Plakka Login Screen");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();

        root.getStylesheets().add(this.getClass().getResource("/View/GUI/application.css").toExternalForm());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
