package client.graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.main.ConnectClient;
import client.main.ServerListener;
import client.main.util.CommandReceiver;

import java.io.IOException;


public class MainGraphic extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loader.load();

        ConnectClient.getInstance();

        CommandReceiver.getInstance().setCurrentControler(loader.getController());

        new Thread(new ServerListener()).start();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
