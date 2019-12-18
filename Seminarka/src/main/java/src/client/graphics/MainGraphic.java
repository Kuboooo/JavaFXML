package src.client.graphics;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.client.main.ConnectClient;
import src.client.main.ServerListener;
import src.client.main.controllerInterface.ControllerInterface;
import src.client.main.util.CommandReceiver;

import java.io.IOException;


public class MainGraphic extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loader.load();

        ConnectClient.getInstance();

        CommandReceiver.getInstance();
        CommandReceiver.setCurrentControler(loader.getController());

        new Thread(new ServerListener()).start();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
