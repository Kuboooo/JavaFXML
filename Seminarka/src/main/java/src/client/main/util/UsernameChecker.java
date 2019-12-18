package src.client.main.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.ConnectClient;
import src.client.main.ServerListener;
import src.client.main.controller.LoginController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class UsernameChecker {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static boolean checkUsername(String username){

        try {

            Socket playerSocket = ConnectClient.getInstance().getPlayerSocket();

            PrintWriter output = new PrintWriter(new OutputStreamWriter(playerSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            output.println(Commands.SET_NAME + username);
            BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));
            String response = input.readLine();
            if (response.startsWith("ok")){
                logger.info("username Ok");
                new Thread(new ServerListener()).start();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("username not Ok");
        return false;
    }
}
