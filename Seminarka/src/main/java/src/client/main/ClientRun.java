package src.client.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.client.main.controller.GameLayoutController;
import src.server.main.ServerRun;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientRun {

    private static final Logger logger = LoggerFactory.getLogger(ClientRun.class);

    private Socket playerSocket;

    private GameLayoutController glc;

    private ThreadingClass tc;

    public ClientRun(){
        try {
            logger.info("startin client");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            playerSocket = new Socket(address, 8000);
            BufferedReader input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream(), StandardCharsets.UTF_8));

            //new Thread(new ThreadingClass(playerSocket, input, glc)).start();

            tc = new ThreadingClass(playerSocket, input);

            Thread t = new Thread(tc);
            t.start();


        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public GameLayoutController getGlc() {
        return glc;
    }

    public void setGlc(GameLayoutController glc) {
        this.glc = glc;
        tc.setGlc(glc);
    }

    public void setPlayerSocket(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }
}


