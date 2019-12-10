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

    private GameLayoutController gml;

    public ClientRun(GameLayoutController gml){
        this.gml = gml;
        System.out.println( gml + " gml z konstrukotru Client Runu");
        try {
            logger.info("startin client");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            Socket clientSocket = new Socket(address, 8000);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);

            System.out.println(gml + " gml pri zalozeni triedy");
            new Thread(new ThreadingClass(clientSocket, input, null, this.gml)).start();
            new Thread(new ThreadingClass(clientSocket, null, output, this.gml)).start();


        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}


