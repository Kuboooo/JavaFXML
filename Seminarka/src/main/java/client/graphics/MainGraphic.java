package client.graphics;

import client.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.ServerRun;

import java.io.IOException;


public class MainGraphic extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MainGraphic.class);
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

        primaryStage.setOnCloseRequest(event -> {
            logger.info("Stage is closing");
            CommanderSender.getInstance().process(Commands.QUIT, "main");
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
