package src.graphics;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.ClientRun;
import src.client.main.controller.GameLayoutController;
import src.client.main.controller.LoginController;
import src.server.main.ServerRun;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class MainGraphic extends Application{

    private static final Logger logger = LoggerFactory.getLogger(MainGraphic.class);

    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    String serverMessage = "";

    @Override
    public void start(Stage primaryStage) {


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button();
        btn.setText("Enter Game");
        GameLayoutController glc = null;
        ClientRun clientRun = new ClientRun();

                Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));

                        root = loader.load();
                        glc =  loader.getController();
                        logger.info("glc " + glc );
                        logger.info("clientRun" + clientRun);
                        logger.info("getPlayerSocket " + clientRun.getPlayerSocket());
                        logger.info("loader" + loader);
                        glc.setPlayerSocket(clientRun.getPlayerSocket());
                        clientRun.setGlc(glc);
                        loader.setRoot(root);
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                try {
                                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientRun.getPlayerSocket().getOutputStream(), StandardCharsets.UTF_8), true);
                                    BufferedReader input = new BufferedReader(new InputStreamReader(clientRun.getPlayerSocket().getInputStream(), StandardCharsets.UTF_8));
                                    logger.info("posiela mmsg " );
                                    String name = userTextField.getText();
                                    pw.println(name);
                                    logger.info("posiela mmsg " + name);
                                    String inputMessage = input.readLine();
                                    if (inputMessage.equals(name)) {



                                        Stage stage = new Stage();
                                        stage.setTitle("My New Stage Title");
                                        stage.setScene(new Scene(root, 450, 450));
                                        stage.show();
                                        // Hide this current window
                                        ((Node) (event.getSource())).getScene().getWindow().hide();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        );
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

        grid.add(btn, 1, 4);

        StackPane root1 = new StackPane();
        root1.getChildren().addAll(grid);
        //root.getChildren().add(btn);

        Scene scene = new Scene(root1, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);

    }
}
