package src.client.graphics;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.client.main.ConnectClient;
import src.client.main.ServerListener;
import src.client.main.util.CommandReceiver;

import java.io.IOException;


public class MainGraphic extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        FXMLLoader loadLFOC = new FXMLLoader(getClass().getResource("/LookingForOpponent.fxml"));
        FXMLLoader loadGLC = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));
        FXMLLoader loadLoginC = new FXMLLoader(getClass().getResource("/Login.fxml"));

        ConnectClient.getInstance();

        loadGLC.load();
        loadLFOC.load();
        loadLoginC.load();

        System.out.println("glc " + loadGLC.getController());

        CommandReceiver.getInstance(loadGLC.getController(), loadLFOC.getController(), loadLoginC.getController());
        new Thread(new ServerListener()).start();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

}
