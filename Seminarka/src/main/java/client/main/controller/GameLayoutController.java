package client.main.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.game.Game;
import client.main.controllerInterface.ControllerInterface;
import client.main.util.CommanderSender;
import client.main.util.Commands;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class GameLayoutController implements Initializable, ControllerInterface {

    private static final Logger logger = LoggerFactory.getLogger(GameLayoutController.class);

    @FXML
    private Label playerName;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button findOpponent;

    private Collection<Button> listOfButtons;

    @FXML
    private TextArea textArea;

    @FXML
    private Button sendMessageChat;

    @FXML
    private TextField textFieldMessage;

    private AnchorPane secondaryLayout;

    public void appendMessage(String message) {
        textArea.appendText("\n" + message);
    }

    @FXML
    public void posliMasage() {
        CommanderSender.getInstance().process(Commands.CHAT, textFieldMessage.getText());
        textFieldMessage.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAllButton();
        transparentAndFocusTraversable();
        textArea.setVisible(false);
        textFieldMessage.setVisible(false);
        sendMessageChat.setVisible(false);
        playerName.setText(Game.getCurrentPlayer().getName());
    }

    public void addAllButton() {
        listOfButtons = new ArrayList();
        listOfButtons.add(button1);
        listOfButtons.add(button2);
        listOfButtons.add(button3);
        listOfButtons.add(button4);
        listOfButtons.add(button5);
        listOfButtons.add(button6);
        listOfButtons.add(button7);
        listOfButtons.add(button8);
        listOfButtons.add(button9);
    }

    private void transparentAndFocusTraversable() {
        for (Button b : listOfButtons) {
            b.setFocusTraversable(false);
            b.setMouseTransparent(true);
        }
    }

    private void setMouseTransparentButtons() {
        for (Button b : listOfButtons) {
            b.setMouseTransparent(false);
        }
    }

    public void setUpForPlay() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                secondaryLayout.getScene().getWindow().hide();
                findOpponent.setVisible(false);
            }
        });
        textArea.setVisible(true);
        textFieldMessage.setVisible(true);
        sendMessageChat.setVisible(true);
        setMouseTransparentButtons();
        setUpGameButtons();
    }

    private void setUpGameButtons() {
        int i = 1;
        for (Button b : listOfButtons) {
            b.setOnAction(new NumberButtonHandler(i));
            i++;
        }
    }

    @FXML
    public void findOpponent() {
        loadUpLFOLayout();
    }

    private void loadUpLFOLayout() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                secondaryLayout = new AnchorPane();

                ProgressBar progressBar = new ProgressBar();
                progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                progressBar.setLayoutX(25.0);
                progressBar.setLayoutY(27.0);
                Button btn = new Button();
                btn.setLayoutX(99.0);
                btn.setLayoutY(63.0);
                btn.setText("Cancel");
                secondaryLayout.getChildren().addAll(progressBar, btn);

                Stage stage = new Stage();
                btn.setOnAction((event) -> {
                    stage.close();
                    CommanderSender.getInstance().process(Commands.CANCEL, null);
                });
                CommanderSender.getInstance().process(Commands.FIND_OPPONENT, null);
                stage.setTitle("Looking 4 opponent title");
                stage.setScene(new Scene(secondaryLayout, 250, 150));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            }
        });
    }

    public void dab(char command, char input) {
        String token = String.valueOf(input);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (command) {
                    case '1':
                        button1.setMouseTransparent(true);
                        button1.setText(token);
                        break;
                    case '2':
                        button2.setMouseTransparent(true);
                        button2.setText(token);
                        break;
                    case '3':
                        button3.setMouseTransparent(true);
                        button3.setText(token);
                        break;
                    case '4':
                        button4.setMouseTransparent(true);
                        button4.setText(token);
                        break;
                    case '5':
                        button5.setMouseTransparent(true);
                        button5.setText(token);
                        break;
                    case '6':
                        button6.setMouseTransparent(true);
                        button6.setText(token);
                        break;
                    case '7':
                        button7.setMouseTransparent(true);
                        button7.setText(token);
                        break;
                    case '8':
                        button8.setMouseTransparent(true);
                        button8.setText(token);
                        break;
                    case '9':
                        button9.setMouseTransparent(true);
                        button9.setText(token);
                        break;
                    default:
                        logger.error("unknown move");
                }
            }
        });
    }

    public void updateIWin() {
        for (Character c : Game.getWinnerList()) {
            highlight(c, "-fx-border-color: #27fe29");
        }
    }

    public void updateOpponentWin() {

        for (Character c : Game.getWinnerList()) {
            highlight(c, "-fx-border-color: #f04929");
        }
    }

    public void highlight(char input, String s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (input) {
                    case '1':
                        button1.setStyle(s);
                        break;
                    case '2':
                        button2.setStyle(s);
                        break;
                    case '3':
                        button3.setStyle(s);
                        break;
                    case '4':
                        button4.setStyle(s);
                        break;
                    case '5':
                        button5.setStyle(s);
                        break;
                    case '6':
                        button6.setStyle(s);
                        break;
                    case '7':
                        button7.setStyle(s);
                        break;
                    case '8':
                        button8.setStyle(s);
                        break;
                    case '9':
                        button9.setStyle(s);
                        break;
                    default:
                        logger.error("unknown char. should never happen");
                }
            }
        });
    }

    class NumberButtonHandler implements EventHandler<ActionEvent> {
        private final int fieldNumber;

        NumberButtonHandler(int fieldNumber) {
            this.fieldNumber = fieldNumber;
        }

        @Override
        public void handle(ActionEvent event) {
            if (Game.isIsMyTurn() && !Game.isiWin()) {
                CommanderSender.getInstance().process(Commands.MOVE, fieldNumber);
            }
        }
    }

}
